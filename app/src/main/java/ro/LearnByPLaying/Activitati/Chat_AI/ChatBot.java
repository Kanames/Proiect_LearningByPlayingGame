package ro.LearnByPLaying.Activitati.Chat_AI;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ro.LearnByPLaying.Utilitare.StringUtils;

public class ChatBot extends AppCompatActivity {
    private String HUMAN = "Human";
    private String BOT = "Ben";
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> responses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activitati","<<<<< IN ChatBot.onCreate() >>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        FloatingActionButton fab = findViewById(R.id.fab);
        final EditText questionRAW = (EditText) findViewById(R.id.input);
        final RecyclerView recyclerView = findViewById(R.id.list_of_messages);
        questions.add("Hi");
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(questions,HUMAN,getApplicationContext(),"HUMAN");
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

                    questions.add("neah nu imi pasa ce vrei");
                    adapter.notifyItemChanged(recyclerView.getAdapter().getItemCount());
                    recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
                }
            }
        });

    }


}
