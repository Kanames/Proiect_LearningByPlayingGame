package ro.LearnByPLaying.Activitati;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.stefan.proiect_learningbyplayinggame.R;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Adapters.RecyclerViewAdapterChat;

public class ChatBotActivity extends AppCompatActivity {
    private static final String TAG = "ChatBotActivity- ";
    private ArrayList<String> conversation = new ArrayList<>();
    private ArrayList<String> typeHumanOrBot = new ArrayList<>();
    private RecyclerViewAdapterChat adapter;
    private RecyclerView recyclerView;
    private static FloatingActionButton sendMsgBTN;
    private EditText questionInput;
private static boolean firstApiCall = true;
private static Context context;
    private static Assistant assistant = new Assistant("2018-05-23");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activitati","<<<<< IN ChatBotActivity.onCreate() >>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        sendMsgBTN     = findViewById(R.id.fab);
        recyclerView   = findViewById(R.id.list_of_messages);
        questionInput  = findViewById(R.id.input);

        Bundle extras = getIntent().getExtras();
        User userObject = null;
        if (extras != null) {
            userObject = (User) extras.getSerializable("SESSION_USER");
        }

        conversation.add("Hi");
        typeHumanOrBot.add("BOT");
        adapter = new RecyclerViewAdapterChat(conversation,userObject.getNickName(),getApplicationContext(),typeHumanOrBot);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        JSONObject credentials = null; // Convert the file into a JSON object

        String username = null;
        String password = null;
        try {
            credentials = new JSONObject(IOUtils.toString(
                    getResources().openRawResource(R.raw.credentials), "UTF-8"
            ));
            // Extract the two values
            username = credentials.getString("username");
            password = credentials.getString("password");
            assistant.setUsernameAndPassword(username,password);
            assistant.setEndPoint("https://gateway.watsonplatform.net/assistant/api");
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("X-Watson-Learning-Opt-Out", "true");
            assistant.setDefaultHeaders(headers);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendMsgBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Activitati",TAG+"onClick");
                String questionProcessesd = questionInput.getText().toString().trim();
                Log.d("Activitati",TAG+"questionProcessesd: "+questionProcessesd);
                questionInput.setText("");
                if(!TextUtils.isEmpty(questionProcessesd)){
                    typeHumanOrBot.add("HUMAN");
                    conversation.add(questionProcessesd);
                    adapter.notifyItemChanged(recyclerView.getAdapter().getItemCount());
                    new AskingWatson().execute(questionProcessesd);
                }
            }
        });
    }
    class AskingWatson extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... strings) {
            //-- watson
            List<String> responseWatson = null;
            try {
                InputData input = new InputData.Builder(strings[0]).build();
                String workspaceId = "9357f8a2-5b1d-4669-a233-7b881b7257d7";
                MessageOptions options;

                if(firstApiCall){
                    Log.d("Activitati", TAG+" first API call");
                    options = new MessageOptions.Builder(workspaceId).input(input).build();
                    firstApiCall = false;
                }else{
                     Log.d("Activitati", TAG+" second API call");
                     options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
                }
                Log.d("Activitati", TAG+"options: " +options);
                MessageResponse response = assistant.message(options).execute();
                Log.i("Activitati", TAG+"response: " +response);
                context=response.getContext();
                responseWatson=response.getOutput().getText();
                Log.i("Activitati", TAG+"response in thread WATSON: " +response);
            } catch (NotFoundException e) {
                // Handle Not Found (404) exception
                Log.e("Activitati", TAG+"NotFoundException WATSON: " + e.getMessage());
            } catch (RequestTooLargeException e) {
                // Handle Request Too Large (413) exception
                Log.e("Activitati", TAG+"RequestTooLargeException WATSON: " + e.getMessage());
            } catch (ServiceResponseException e) {
                // Base class for all exceptions caused by error responses from the service
                Log.e("Activitati", TAG+"ServiceResponseException WATSON: " + e.getMessage());
            }
            return responseWatson;
        }
        @Override
        protected void onPostExecute(List<String> results)
        {
            // call an external function as a result
            for (int i = 0 ; i < results.size() ; i++) {
                Log.d("Activitati", TAG + "WATSON reply before inserting in activity: " + results.get(i));
                typeHumanOrBot.add("BOT");
                conversation.add(results.get(i));
            }
            adapter.notifyItemChanged(recyclerView.getAdapter().getItemCount());
            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
        }
    }
}
