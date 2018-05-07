package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.StringUtils;

import static ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils.isUserRegistered;
import static ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils.savingUSER;

public class RegisterActivity extends AppCompatActivity {
    public static  EditText inputEmail,inputPassword1, inputPassword2;
    private TextInputLayout inputLayoutEmail,inputLayoutPass1,inputLayoutPass2;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button signUp;
    public static CheckBox checkBoxAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activitati", "<<< Intrat in RegisterActivity.onCreate >>>");
        super.onCreate(savedInstanceState);

        //Initializarea resurselor------
        setContentView(R.layout.activity_register);
        signUp         = findViewById(R.id.Register_btnRegister);
        inputPassword1 = findViewById(R.id.Register_editTextPass1);
        inputPassword2 = findViewById(R.id.Register_editTextPass2);
        inputEmail     = findViewById(R.id.Register_editTextEmail);
        checkBoxAgree  = findViewById(R.id.Register_checkBoxAgree);
        inputLayoutPass1 =  findViewById(R.id.Register_textInputLayoutPass1);
        inputLayoutPass2 =  findViewById(R.id.Register_textInputLayoutPass2);
        inputLayoutEmail =  findViewById(R.id.Register_textInputLayoutEmail);
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
        getSupportActionBar().setTitle(R.string.RegisterToolbarString);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getString(R.string.RegisterToolbarSecondaryString));
        //-------------------------------------------------------------------------

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    StringUtils.controlTextInput(inputEmail,getString(R.string.error_empty,"E-mail"));
                    StringUtils.controlTextInput(inputPassword1,inputLayoutPass1,getString(R.string.error_empty,"password"),true,8l,getString(R.string.error_weak_password,"8"));
                    StringUtils.controlTextInput(inputPassword2,inputLayoutPass2,getString(R.string.error_empty,"password"),true,8l,getString(R.string.error_weak_password,"8"));
                    StringUtils.control2Passwords(inputPassword1,inputPassword2,inputLayoutPass1,inputLayoutPass2,getString(R.string.error_password_match));

                    final String emailStr     = inputEmail.getText().toString().trim();
                    final String password1Str = inputPassword1.getText().toString().trim();

                    if (!checkBoxAgree.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Please agree with the terms", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(emailStr, password1Str)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            Log.e("Activitati", "RegisterActivity- FirebaseAuthWeakPasswordException: " + e.getMessage());
                                            Toast.makeText(RegisterActivity.this, getString(R.string.error_weak_password), Toast.LENGTH_SHORT).show();
                                            inputLayoutPass1.setError(getString(R.string.error_weak_password));
                                            inputLayoutPass2.setError(getString(R.string.error_weak_password));
                                            inputLayoutPass1.requestFocus();
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            Log.e("Activitati", "RegisterActivity- FirebaseAuthInvalidCredentialsException: " + e.getMessage());
                                            Toast.makeText(RegisterActivity.this, getString(R.string.error_invalid_email), Toast.LENGTH_SHORT).show();
                                            inputLayoutEmail.setError(getString(R.string.error_invalid_email));
                                            inputEmail.requestFocus();
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            Log.e("Activitati", "RegisterActivity- FirebaseAuthUserCollisionException: " + e.getMessage());
                                            Toast.makeText(RegisterActivity.this, getString(R.string.error_user_exists) + task.getException(), Toast.LENGTH_SHORT).show();
                                            inputLayoutEmail.setError(getString(R.string.error_user_exists));
                                            inputEmail.requestFocus();
                                        } catch (Exception e) {
                                            Log.e("Activitati", "RegisterActivity- Exception: " + e.getMessage());
                                        }
                                    } else {
                                        User userObject = new User();
                                        FirebaseUser userFirebaseObject = task.getResult().getUser();
                                        Log.d("Activitati", "RegisterActivity- userFirebaseObject.uid= " + userFirebaseObject.getUid());
                                        userObject.setUserFirebaseID(userFirebaseObject.getUid());
                                        userObject.setDateAccountCreated(new Date().toString());
                                        userObject.setEmailAddress(emailStr);
                                        userObject.setPassword(password1Str);
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.putExtra("SESSION_USER", userObject);
                                        savingUSER(userObject);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }catch (Exception e){
                    Log.e("Activitati", "RegisterActivity- Error register: "+e.getMessage());
                }

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
            Log.d("Activitati", "RegisterActivity- check box agreements status: checked");
            Intent intent = new Intent(v.getContext(), RegisterAgreementsActivity.class);
            startActivity(intent);
        }else{
            Log.d("Activitati", "RegisterActivity- check box agreements status: unchecked");
        }
    }
}
