package ro.LearnByPLaying.Activitati;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import ro.LearnByPLaying.Utilitare.StringUtils;

public class ForgotPasswordActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText inputEmail;
    private TextInputLayout textInputLayoutEmail;
    private ProgressBar progressBar;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.forgotPass_toolbar);
        auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.forgotPass_ProgressBar);
        btnReset    = (Button)      findViewById(R.id.forgotPass_button);
        inputEmail  = (EditText)    findViewById(R.id.forgotPass_editTextEmail);
        textInputLayoutEmail  = (TextInputLayout)    findViewById(R.id.forgotPass_textInputLayoutEmail);

        // Sets the Toolbar to act as the ActionBar for this Activity window.------
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        //-------------------------------------------------------------------------

        // Display icon in the toolbar---------------------------------------------
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.ForgotPass_Toolbar_Title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle(getString(R.string.Toolbar_TitleSecondary));
        //-------------------------------------------------------------------------
        TextView textView = findViewById(R.id.forgotPass_textViewDetails);
        textView.setText(Html.fromHtml(getString(R.string.ForgotPassDetails)));
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    StringUtils.controlTextInput(inputEmail,textInputLayoutEmail,getString(R.string.error_empty,"E-mail"));
                    String emailStr = inputEmail.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    auth.sendPasswordResetEmail(emailStr)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgotPasswordActivity.this, getString(R.string.error_firebase_reset_mail_sent), Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(ForgotPasswordActivity.this, getString(R.string.error_firebase_reset_mail_fail), Toast.LENGTH_SHORT).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }catch(Exception e){

                }
            }
        });
    }
}