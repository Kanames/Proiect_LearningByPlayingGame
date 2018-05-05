package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stefan.proiect_learningbyplayinggame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.security.auth.login.LoginException;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;

import static ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabHello.user;


public class LoginActivity extends AppCompatActivity {
    //Declarare resurse------------------
    private static String USER_FIREBASE_ID;
    private static String USER_OBJECT;
    private FirebaseAuth auth;
    private Button btnLogin;
    private EditText emailUser, parolaUser;
    private TextView register, forgotPass;
    private ProgressBar progressBar;
    private Intent intent;
    //-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("Activitati", "<<< Intrat in LoginActivity >>>");

        //Initializare resurse------------------
        auth = FirebaseAuth.getInstance(); //Get Firebase auth instance
        btnLogin    = findViewById(R.id.login_button);
        emailUser   = findViewById(R.id.editTextEmail);
        parolaUser  = findViewById(R.id.login_editTextPassword);
        progressBar = findViewById(R.id.login_progressBar);
        register    = findViewById(R.id.login_textViewCreateAccount);
        forgotPass  = findViewById(R.id.login_textViewForgotPassword);
        intent = new Intent(LoginActivity.this, CreatingProfile.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) { //Comming from Register to Login
            User userObject = (User) extras.getSerializable("SESSION_USER");
            Log.d("Activitati", getClass().getName()+" FirebaseUserID"+userObject.getUserFirebaseID());
            intent.putExtra("SESSION_USER", userObject);
        }
        //--------------------------------------

        //Flow Logic apasare button------------
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Activitati", "Clicked Login btn !");
                //Verificare resurse--------------------
                try {
                    if (TextUtils.isEmpty(emailUser.getText().toString())) {
                        emailUser.requestFocus();
                        emailUser.setError("Please enter your e-mail");
                        throw new LoginException("Error: please enter the e-mail");
                    }
                    if (TextUtils.isEmpty(parolaUser.getText().toString())) {
                        parolaUser.requestFocus();
                        parolaUser.setError("Please enter the password");
                        throw new LoginException("Error: please fill the password");
                    }
                    String emailStr = emailUser.getText().toString();
                    String passStr = parolaUser.getText().toString();
                    Log.d("Activitati", "numeUser: " + emailStr + " parolaUser: " + passStr);
                    //--------------------------------------
                    progressBar.setVisibility(View.VISIBLE);
                    //authenticate user
                    auth.signInWithEmailAndPassword(emailUser.getText().toString(), parolaUser.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (parolaUser.length() < 8) {
                                            parolaUser.setError("Error: password has minim 8 characters");
                                        }
                                    } else {
                                        USER_FIREBASE_ID = FirebaseRealtimeDBUtils.getUserFirebaseID(auth);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_LONG).show();
                            } else if (e instanceof FirebaseAuthInvalidUserException) {
                                Toast.makeText(LoginActivity.this, "Incorrect email address", Toast.LENGTH_LONG).show();
                            } else {
                                String errorCode = ((FirebaseAuthInvalidUserException) e).getErrorCode();
                                if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                                    Log.e("Activitati", "No matching account found");
                                } else if (errorCode.equals("ERROR_USER_DISABLED")) {
                                    Log.e("Activitati", "User account has been disabled");
                                    Toast.makeText(LoginActivity.this, "User account has been disabled", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.e("Activitati", e.getLocalizedMessage());
                                }
                            }
                        }
                    });
                } catch (LoginException el) {
                    Log.e("Activitati", getClass().getName()+" "+el.getMessage());
                } catch (Exception e) {
                    Log.e("Activitati", getClass().getName()+" "+e.getMessage());
                    Toast.makeText(LoginActivity.this, "We are sorry, we have a problem...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //--------------------------------------
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Activitati", getClass().getName()+" flowing to -> RegisterActivity ");
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Activitati", getClass().getName()+" flowing to -> ForgotPasswordActivity ");
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
