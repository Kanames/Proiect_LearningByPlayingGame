package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.stefan.proiect_learningbyplayinggame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputEmail,inputPassword1, inputPassword2;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button signUp;
    private CheckBox checkBoxAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onCreate >>>");
        super.onCreate(savedInstanceState);
        //Initializarea resurselor------
        setContentView(R.layout.activity_register);
        signUp = (Button) findViewById(R.id.btnRegister);
        inputPassword1 = (EditText) findViewById(R.id.editTextPass1);
        inputPassword2 = (EditText) findViewById(R.id.editTextPass2);
        inputEmail = (EditText) findViewById(R.id.editTextEmail);
        checkBoxAgree = (CheckBox) findViewById(R.id.checkBoxAgree);
        //------------------------------

        //Get Firebase auth instance----
        auth = FirebaseAuth.getInstance();
        //------------------------------

        //Find the toolbar view inside the activity layout------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //------------------------------------------------------

        // Sets the Toolbar to act as the ActionBar for this Activity window.------
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        //-------------------------------------------------------------------------

        // Display icon in the toolbar---------------------------------------------
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Register form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Learn java better and smarter");
        //-------------------------------------------------------------------------



        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password1 = inputPassword1.getText().toString().trim();
                String password2 = inputPassword2.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(getApplicationContext(), "Complete both input passswords!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password2.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Second password too short,minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password1.equals(password2)) {
                    Toast.makeText(getApplicationContext(), "Passwords don't match, Please check again!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    //Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                    try {
                                        throw task.getException();
//                                    } catch(FirebaseAuthWeakPasswordException e) {
//                                        mTxtPassword.setError(getString(R.string.error_weak_password));
//                                        mTxtPassword.requestFocus();
//                                    } catch(FirebaseAuthInvalidCredentialsException e) {
//                                        mTxtEmail.setError(getString(R.string.error_invalid_email));
//                                        mTxtEmail.requestFocus();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        Log.e("Activitati", "FirebaseAuthUserCollisionException: "+e.getMessage());
                                        Toast.makeText(RegisterActivity.this, getString(R.string.error_user_exists) + task.getException(),Toast.LENGTH_SHORT).show();
                                        inputEmail.setError(getString(R.string.error_user_exists));
                                        inputEmail.requestFocus();
                                    } catch(Exception e) {
                                        Log.e("Activitati", "Exception: "+e.getMessage());
                                    }
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
        Log.d("Activitati", "<<< Iesit din RegisterActivity.onCreate >>>");
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    @Override
    protected void onPause() {
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onPause >>>");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onStop >>>");
        inputEmail.setText("");
        inputPassword1.setText("");
        inputPassword2.setText("");
        super.onStop();
    }
    @Override
    protected void onRestart(){
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onRestart >>>");
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onDestroy >>>");
        super.onDestroy();
    }

    public void checkbox_clicked(View v)
    {
        if(checkBoxAgree.isChecked()){
            Log.d("Activitati", "check box agreements status: checked");
            Intent intent = new Intent(v.getContext(), RegisterAgreementsActivity.class);
            startActivity(intent);
        }else{
            Log.d("Activitati", "check box agreements status: unchecked");
        }
    }
}
