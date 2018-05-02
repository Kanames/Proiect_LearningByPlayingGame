package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stefan.proiect_learningbyplayinggame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.HashMap;

import javax.security.auth.login.LoginException;

import ro.LearnByPLaying.FlowActivitati.FlowLogin;

public class LoginActivity extends AppCompatActivity {
    //Declarare resurse------------------
    final FlowLogin FLOW = new FlowLogin();
    private FirebaseAuth auth;
    private Button btnLogin;
    private EditText emailUser, parolaUser;
    private TextView register, forgotPass;
    private ProgressBar progressBar;
    //-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("Activitati", "<<< Intrat in LoginActivity >>>");
        //Initializare resurse------------------
        btnLogin   = findViewById(R.id.login_button);
        emailUser  = findViewById(R.id.editTextEmail);
        parolaUser = findViewById(R.id.login_editTextPassword);
        auth       = FirebaseAuth.getInstance(); //Get Firebase auth instance
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        register    = findViewById(R.id.login_textViewCreateAccount);
        forgotPass  = findViewById(R.id.login_textViewForgotPassword);
        //--------------------------------------

        //--------------------------------------
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Activitati", "Clicked Login btn !");
                //Verificare resurse--------------------
                try {
                    FLOW.execute(emailUser, parolaUser);
                } catch (LoginException el) {
                    Log.e("Activitati", el.getMessage());
                }
                //--------------------------------------
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    //authenticate user
                    auth.signInWithEmailAndPassword(emailUser.getText().toString(), parolaUser.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (parolaUser.length() < 6) {
                                            parolaUser.setError("Password has minim 8 characters");
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Eroare ce ma sperie", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                } catch (Exception e) {
                    Log.e("Activitati", e.getMessage());
                    Toast.makeText(LoginActivity.this, "Damn son, o exceptie urata!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
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
        emailUser.setText("");
        parolaUser.setText("");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Activitati", "<<< Intrat in LoginActivity.onRestart >>>");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("Login", "<<< Intrat in LoginActivity.onResume >>>");
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        Log.d("Activitati", "<<< Intrat in LoginActivity.onDestroy >>>");
        super.onDestroy();
    }

}
