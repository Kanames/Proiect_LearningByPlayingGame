package ro.LearnByPLaying.Activitati.MainSubActivities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;

import java.util.ArrayList;
import java.util.HashMap;

import ro.LearnByPLaying.Activitati.MainActivity;
import ro.LearnByPLaying.Activitati.MainSubActivities.MenuItems.RecyclerViewUpdateProfile;
import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;

import static ro.LearnByPLaying.Activitati.CreatingProfile.USER_OBJECT;
import static ro.LearnByPLaying.Utilitare.StringUtils.trfOut;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "CreatingProfile- ";
    private static User SESSION_USER;
    private static HashMap<String,ArrayList<String>> values = new HashMap<>();
    private static ArrayList<String> afisareProprietati = new ArrayList<>();
    private static ArrayList<String> valoareProprietati = new ArrayList<>();
    private static ArrayList<String> hinturi = new ArrayList<>();
    private static ArrayList<View.OnClickListener> clickListeners = new ArrayList<>();
    private static Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Find the toolbar view inside the activity layout------
        Toolbar toolbar = findViewById(R.id.toolbar);
        updateBtn = findViewById(R.id.updateBtn);

        //------------------------------------------------------

        // Sets the Toolbar to act as the ActionBar for this Activity window.------
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        //-------------------------------------------------------------------------

        // Display icon in the toolbar---------------------------------------------
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getString(R.string.RegisterToolbarSecondaryString));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            SESSION_USER  = (User) extras.getSerializable("SESSION_USER");
        }
        Log.d("Activitati","0000000000000000000000000000000 userObject: "+trfOut(SESSION_USER));



        afisareProprietati.add("Nickname: ");
        valoareProprietati.add(SESSION_USER.getNickName());
        hinturi.add("Change the nickname ? ");
        clickListeners.add(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText x = (EditText) findViewById(R.id.inputLayoutUpdateValue);
                TextView y = (TextView) findViewById(R.id.textViewValue);
                String your_text = x.getText().toString();
                Log.d("Activitati","new Nickname"+your_text);
                HashMap toModify = new HashMap();
                toModify.put("nickName", your_text);
                SESSION_USER.setNickName(your_text);
                y.setText(your_text);
                MainActivity.SESSION_USER.setNickName(your_text);
                FirebaseRealtimeDBUtils.updateUSER(SESSION_USER,toModify);
            }
        });

        afisareProprietati.add("First name: ");
        valoareProprietati.add(SESSION_USER.getFirstName());
        hinturi.add("Change the first name ?");
        clickListeners.add(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText x = (EditText) findViewById(R.id.inputLayoutUpdateValue);
                TextView y = (TextView) findViewById(R.id.textViewValue);
                String your_text = x.getText().toString();
                Log.d("Activitati","new First name: "+your_text);
                HashMap toModify = new HashMap();
                toModify.put("firstname", your_text);
                SESSION_USER.setFirstName(your_text);
                y.setText(your_text);
                MainActivity.SESSION_USER.setFirstName(your_text);
                FirebaseRealtimeDBUtils.updateUSER(SESSION_USER,toModify);
            }
        });

        afisareProprietati.add("Last name: ");
        valoareProprietati.add(SESSION_USER.getLastName());
        hinturi.add("Change the last name ?");
        clickListeners.add(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText x = (EditText) findViewById(R.id.inputLayoutUpdateValue);
                TextView y = (TextView) findViewById(R.id.textViewValue);
                String your_text = x.getText().toString();
                Log.d("Activitati","new Last name: "+your_text);
                HashMap toModify = new HashMap();
                toModify.put("lastname", your_text);
                SESSION_USER.setLastName(your_text);
                y.setText(your_text);
                MainActivity.SESSION_USER.setLastName(your_text);
                FirebaseRealtimeDBUtils.updateUSER(SESSION_USER,toModify);
            }
        });

        values.put("afisareProprietati",afisareProprietati);
        values.put("valoareProprietati",valoareProprietati);
        values.put("hinturi",hinturi);

        final RecyclerViewUpdateProfile adapter = new RecyclerViewUpdateProfile(getApplicationContext(),values,clickListeners);
        Log.d("Activitati", TAG+" adapter: "+ trfOut(adapter));
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }
}
