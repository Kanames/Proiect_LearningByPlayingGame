package ro.LearnByPLaying.Activitati;

import android.support.design.widget.TextInputLayout;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ro.LearnByPLaying.Adapters.RecyclerViewUpdateProfile;
import ro.LearnByPLaying.Beans.SettingProfileView;
import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;
import ro.LearnByPLaying.Utilitare.StringUtils;

import static ro.LearnByPLaying.Utilitare.StringUtils.trfOut;
/*
    Activity made for editing the profile of the user.
    (Licenta)
 */
public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "CreatingProfileActivity- ";//Variables used for logging
    private static User SESSION_USER;                            //The user that comes from session
    private static HashMap<String,ArrayList<String>> values = new HashMap<>(); //represents the content of the RecyclerViewUpdateProfile
       private static ArrayList<String> afisareProprietati = new ArrayList<>();  // parameter for the RecyclerViewUpdateProfile: What we are gone change ?
       private static ArrayList<String> valoareProprietati = new ArrayList<>();  // parameter for the RecyclerViewUpdateProfile: What values is it ?
       private static ArrayList<String> hinturi = new ArrayList<>();             // parameter for the RecyclerViewUpdateProfile: What hints we can offer ?
       private static ArrayList<View.OnClickListener> clickListeners = new ArrayList<>(); // parameter for the RecyclerViewUpdateProfile: What action should it do ?

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
        try {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setSubtitle(getString(R.string.RegisterToolbarSecondaryString));
        } catch (Exception e) {
            Log.e("Activitati", TAG + "Problem with the Toolbar: "+e.getMessage());
        }

        values.clear();  //------------- Cleaning the variables ----
          afisareProprietati.clear();
          valoareProprietati.clear();
          hinturi.clear();
          clickListeners.clear();
        //----------------------------------------------------------


        Log.d("Activitati",TAG+"UserObject that comes from the session: "+afisareProprietati.size());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            SESSION_USER  = (User) extras.getSerializable("SESSION_USER");
            Log.e("Activitati", TAG + "SESSION_USER(problemes): "+SESSION_USER);
        }


        afisareProprietati.add("Nickname: ");
        valoareProprietati.add(SESSION_USER.getNickName());
        hinturi.add("Change the nickname ? ");
        clickListeners.add(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout textInputLayout =  findViewById(R.id.inputLayoutUpdate);
                EditText inputValue =  findViewById(R.id.inputLayoutUpdateValue);
                TextView textView =  findViewById(R.id.textViewValue);
                String textRawNickname = inputValue.getText().toString();
                inputValue.setText("");
                inputValue.getText().clear();

                try {
                    StringUtils.controlTextInput(inputValue,textInputLayout, getString(R.string.error_empty));
                } catch (Exception e) {
                    Log.e("Activitati",TAG+"User Nickname Error: "+e.getMessage());
                }
                Log.d("Activitati",TAG+"User new Nickname: "+textRawNickname);
                HashMap toModify = new HashMap();
                toModify.put("nickName", textRawNickname);
                SESSION_USER.setNickName(textRawNickname);
                textView.setText(textRawNickname);
                MainActivity.SESSION_USER.setNickName(textRawNickname);
                FirebaseRealtimeDBUtils.updateUSER(SESSION_USER,toModify);
            }
        });

        afisareProprietati.add("First name: ");
        valoareProprietati.add(SESSION_USER.getFirstName());
        hinturi.add("Change the first name ?");
        clickListeners.add(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.inputLayoutUpdate);
                EditText inputValue = (EditText) findViewById(R.id.inputLayoutUpdateValue);
                TextView textView = (TextView) findViewById(R.id.textViewValue);
                String your_text = textView.getText().toString();
                try {
                    StringUtils.controlTextInput(inputValue,textInputLayout, getString(R.string.error_empty));
                } catch (Exception e) {
                    Log.e("Activitati","Error: "+e.getMessage());
                }
                Log.d("Activitati","new First name: "+your_text);
                HashMap toModify = new HashMap();
                toModify.put("firstname", your_text);
                SESSION_USER.setFirstName(your_text);
                textView.setText(your_text);
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

    @Override
    protected void onPause() {
        Log.d("Activitati", TAG+" onPause()");
        super.onPause();
        this.finish();
    }
}
