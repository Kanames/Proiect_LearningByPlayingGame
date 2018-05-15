package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.security.auth.login.LoginException;
import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;
import ro.LearnByPLaying.Utilitare.StringUtils;


public class LoginActivity extends AppCompatActivity {
    //Declarare resurse------------------
    private static final String TAG = "LoginActivity- ";
    private static String USER_FIREBASE_ID;
    private FirebaseAuth auth;
    private Button btnLogin;
    private EditText emailUser, parolaUser;
    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
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
        textInputLayoutEmail = findViewById(R.id.login_textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.login_textInputLayoutPassword);
        intent = new Intent(LoginActivity.this, CreatingProfileActivity.class);
        //---Daca venim din RegisterActivity----
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            User userObject = (User) extras.getSerializable("SESSION_USER");
            if(userObject != null ){
                Log.d("Activitati", TAG+" < Comming from Register to Login >");
                intent.putExtra("SESSION_USER", userObject);
            }
        }
        //--------------------------------------

        //Flow Logic apasare button------------
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Activitati", TAG+"  Clicked Login button !");
                //Verificare resurse--------------------
                try {
                    StringUtils.controlTextInput(emailUser,getString(R.string.error_empty,"E-mail"));
                    StringUtils.controlTextInput(parolaUser,getString(R.string.error_empty,"password"));
                    String emailStr = emailUser.getText().toString();
                    String passStr = parolaUser.getText().toString();
                    Log.d("Activitati", TAG+"numeUser: " + emailStr + " parolaUser: " + passStr);
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
                                    } else {
                                        //---Getting the UserID-----------
                                        USER_FIREBASE_ID = FirebaseRealtimeDBUtils.getUserFirebaseID(auth);
                                        Log.d("Activitati",TAG+" object auth: "+StringUtils.trfOut(auth));
                                        Log.d("Activitati",TAG+" USER_FIREBASE_ID: "+USER_FIREBASE_ID);
                                        //----Getting the USER
                                        final User[] user = new User[1];
                                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference ref = database.getReference("USERS");
                                        ref.child(USER_FIREBASE_ID).limitToFirst(20).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                              @Override
                                                                                              public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                  user[0] = dataSnapshot.getValue(User.class);
                                                                                                  Log.d("Activitati", TAG+"FirstNameDB email: " + user[0].getFirstName());
                                                                                                  intent.putExtra("SESSION_USER", user[0]);
                                                                                                  if(user[0].getNickName() != null) {
                                                                                                      Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                                                                                                      intent2.putExtra("SESSION_USER", user[0]);
                                                                                                      startActivity(intent2);
                                                                                                      finish();
                                                                                                  }else{
                                                                                                      startActivity(intent);
                                                                                                      finish();
                                                                                                  }

                                                                                              }
                                                                                              @Override
                                                                                              public void onCancelled(DatabaseError databaseError) {
                                                                                                  Log.d("Activitati", TAG + " DB ERROR: " + databaseError.getMessage());
                                                                                              }
                                                                                          });
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this, getString(R.string.error_firebase_wrong_password) , Toast.LENGTH_LONG).show();
                            } else if (e instanceof FirebaseAuthInvalidUserException) {
                                Toast.makeText(LoginActivity.this, getString(R.string.error_firebase_account_not_found), Toast.LENGTH_LONG).show();
                            } else {
                                String errorCode = ((FirebaseAuthInvalidUserException) e).getErrorCode();
                                if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                                    Log.e("Activitati", TAG+"No matching account found");
                                } else if (errorCode.equals("ERROR_USER_DISABLED")) {
                                    Log.e("Activitati", TAG+"User account has been disabled");
                                    Toast.makeText(LoginActivity.this, "User account has been disabled", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.e("Activitati", TAG+"Exception: "+e.getLocalizedMessage());
                                }
                            }
                        }
                    });
                } catch (LoginException el) {
                    Log.e("Activitati", TAG+el.getMessage());
                } catch (Exception e) {
                    Log.e("Activitati", TAG+e.getMessage());
                    if(!e.getMessage().contains("Error: please fill the input")){
                        Toast.makeText(LoginActivity.this, "We are sorry, we have a problem...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //--------------------------------------
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Activitati", TAG+" flowing to -> RegisterActivity ");
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Activitati", TAG+" flowing to -> ForgotPasswordActivity ");
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
