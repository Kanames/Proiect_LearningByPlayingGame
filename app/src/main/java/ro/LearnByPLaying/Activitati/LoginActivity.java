package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stefan.proiect_learningbyplayinggame.R;


import javax.security.auth.login.LoginException;

import ro.LearnByPLaying.FlowActivitati.FlowLogin;

public class LoginActivity extends AppCompatActivity {
    final FlowLogin FLOW = new FlowLogin();
    Button btnLogin;
    EditText numeUser,parolaUser;
    TextView register,forgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("Activitati", "<<< Intrat in LoginActivity >>>");
        try{
            Log.d("Activitati","Clicked ! btnLogin");
            btnLogin = findViewById(R.id.buttonLogin);
            numeUser = findViewById(R.id.editTextName);
            parolaUser = findViewById(R.id.editTextPassword);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        FLOW.execute(numeUser,parolaUser);
                    }catch(LoginException el) {
                        Log.e("Activitati", el.getMessage().toString());
                        Toast.makeText(LoginActivity.this, el.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(LoginActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            register = findViewById(R.id.textViewCreateAccount);
            register.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });

        }catch(Exception e){
            Toast.makeText(this, "Damn son ! o exceptie !", Toast.LENGTH_SHORT).show();
        }

        Log.d("Activitati", "<<< Iesit din LoginActivity >>>");
    }
    @Override
    protected void onPause() {
        Log.d("Activitati", "<<< Intrat in LoginActivity.onPause >>>");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("Activitati", "<<< Intrat in LoginActivity.onStop >>>");
        numeUser.setText("");
        parolaUser.setText("");
        super.onStop();
    }
    @Override
    protected void onRestart(){
        Log.d("Activitati", "<<< Intrat in LoginActivity.onRestart >>>");
        super.onRestart();
    }
    @Override
    protected void onResume(){
        Log.d("Login", "<<< Intrat in LoginActivity.onResume >>>");
        super.onResume();
    }
    @Override
    protected void onDestroy(){
        Log.d("Activitati", "<<< Intrat in LoginActivity.onDestroy >>>");
        super.onDestroy();
    }

}
