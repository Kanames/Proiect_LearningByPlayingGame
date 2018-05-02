package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;

public class RegisterAgreementsActivity extends AppCompatActivity {
    private Button btnAgreementYES;
    private Button btnAgreementNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agreements);
        btnAgreementYES = findViewById(R.id.register_btnAgreementAcceptedYES);
        btnAgreementNo  = findViewById(R.id.register_btnAgreementAcceptedNO);
        TextView textView = findViewById(R.id.register_textViewAgreementsDetails);
        textView.setText(Html.fromHtml(getString(R.string.RegisterAgreementsDetails)));
        btnAgreementYES.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btnAgreementNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
