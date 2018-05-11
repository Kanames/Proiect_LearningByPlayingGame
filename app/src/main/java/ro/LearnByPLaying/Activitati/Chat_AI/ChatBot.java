package ro.LearnByPLaying.Activitati.Chat_AI;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;
import com.google.android.gms.common.api.Response;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.StringUtils;

import static ro.LearnByPLaying.Utilitare.StringUtils.trfOut;

public class ChatBot extends AppCompatActivity {
    private String HUMAN = "Human";
    private String BOT = "Ben";
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> responses = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activitati","<<<<< IN ChatBot.onCreate() >>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        FloatingActionButton fab = findViewById(R.id.fab);
        final EditText questionRAW = (EditText) findViewById(R.id.input);
        recyclerView = findViewById(R.id.list_of_messages);
        questions.add("Hi");

        Bundle extras = getIntent().getExtras();
        User userObject = null;
        if (extras != null) {
            userObject = (User) extras.getSerializable("SESSION_USER");
        }

        adapter = new RecyclerViewAdapter(questions,userObject.getNickName(),getApplicationContext(),HUMAN);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionStr =questionRAW.getText().toString();
                if(!TextUtils.isEmpty(questionStr)){
                    questionRAW.setText("");
                    questions.add(questionStr);
                    adapter.notifyItemChanged(recyclerView.getAdapter().getItemCount());
                    Log.d("Activitati","recyclerView.getAdapter().getItemCount()-1 -----> "+String.valueOf(recyclerView.getAdapter().getItemCount()-1));
                    new AskingWatson().execute(questionStr);
                }
            }
        });
    }
    class AskingWatson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            //-- watson
            JSONObject credentials = null; // Convert the file into a JSON object
            Log.d("Activitati", " strings[0] WATSON: " + strings[0]);
            String responseWatson = null;
            try {
                credentials = new JSONObject(IOUtils.toString(
                        getResources().openRawResource(R.raw.credentials), "UTF-8"
                ));
                // Extract the two values
                String username = credentials.getString("username");
                String password = credentials.getString("password");

                Assistant assistant = new Assistant("2018-05-10");
                assistant.setUsernameAndPassword(username,password);
                assistant.setEndPoint("https://gateway.watsonplatform.net/assistant/api");
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("X-Watson-Learning-Opt-Out", "true");
                assistant.setDefaultHeaders(headers);
                InputData input = new InputData.Builder( strings[0]).build();

                String workspaceId = "9357f8a2-5b1d-4669-a233-7b881b7257d7";

                MessageOptions options = new MessageOptions.Builder(workspaceId)
                        .input(input)
                        .build();

                MessageResponse response = assistant.message(options).execute();

                Log.d("Activitati", " getOutput WATSON: " +trfOut(response.getOutput().getText()));
                Log.d("Activitati", " getOutput get(0) WATSON: " +response.getOutput().getText().get(0));
                responseWatson = response.getOutput().getText().get(0);
                Log.d("Activitati", " response WATSON: " +response);
            } catch (NotFoundException e) {
                // Handle Not Found (404) exception
                Log.d("Activitati", " NotFoundException WATSON: " + e.getMessage());
            } catch (RequestTooLargeException e) {
                // Handle Request Too Large (413) exception
                Log.d("Activitati", " RequestTooLargeException WATSON: " + e.getMessage());
            } catch (ServiceResponseException e) {
                // Base class for all exceptions caused by error responses from the service
                Log.d("Activitati", " ServiceResponseException WATSON: " + e.getMessage());
            } catch (JSONException e) {
                Log.d("Activitati", " JSONException WATSON: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("Activitati", " IOException WATSON: " + e.getMessage());
                e.printStackTrace();
            }
            return responseWatson;
        }
        @Override
        protected void onPostExecute(String result)
        {
            // call an external function as a result
            Log.d("Activitati", " result!!!! WATSON: " + result);
            questions.add(result);
            adapter.notifyItemChanged(recyclerView.getAdapter().getItemCount());
            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
        }
    }
}
