package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ro.LearnByPLaying.Utilitare.TypeWriter;

/**
 * Created by Stefan on 5/2/2018.
 */
public class TabHello extends Fragment {
    private static final String TAG = "Fragment-TabHello- ";
    private static String msgDB;
    private static View view;
    private static TextView editTextTitle,editText,bodyJAVA,secondaryTitleJava,primaryTitleJava;
    private static TextView bodySQL,secondaryTitleSql,primaryTitleSql;
    private static View whyJavaVIEW,whySqlVIEW;
    private static ImageView logoJava,logoSql;
    public TabHello() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("Activitati", "<<< Intrat in TabHello >>>");
        view = inflater.inflate(R.layout.creating_profile_tab_hello, container, false);
        editTextTitle = view.findViewById(R.id.CP_HelloAboutAPPTitle);
           editTextTitle.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPPTitle)));
        editText = view.findViewById(R.id.CP_HelloAboutAPP);
           editText.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPP)));


        whyJavaVIEW  = view.findViewById(R.id.rez_java);

        logoJava =  whyJavaVIEW.findViewById(R.id.logo);
        logoJava.setImageResource(R.drawable.create_profile_java_logo);
        primaryTitleJava = whyJavaVIEW.findViewById(R.id.PrimaryTitle);
        primaryTitleJava.setText("Why JAVA 8 ?");
        secondaryTitleJava = whyJavaVIEW.findViewById(R.id.SecondaryTitle);
        secondaryTitleJava.setText("A new way for functional programming");
        bodyJAVA = whyJavaVIEW.findViewById(R.id.BodyText);
        bodyJAVA.setText(Html.fromHtml(getString(R.string.aboutJava)));


        whySqlVIEW  = view.findViewById(R.id.rez_sql);

        logoSql =  whySqlVIEW.findViewById(R.id.logo);
        logoSql.setImageResource(R.drawable.create_profile_sql_logo);
        primaryTitleSql = whySqlVIEW.findViewById(R.id.PrimaryTitle);
        primaryTitleSql.setText("Why SQL ?");
        secondaryTitleSql = whySqlVIEW.findViewById(R.id.SecondaryTitle);
        secondaryTitleSql.setText("A basic concept to maintain and use");
        bodySQL = whySqlVIEW.findViewById(R.id.BodyText);
        bodySQL.setText(Html.fromHtml(getString(R.string.aboutSql)));

//        whySqlVIEW.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bodySQL.setVisibility(View.VISIBLE);
//                bodySQL.setText(Html.fromHtml(getString(R.string.aboutSql)));
//            }
//        });


        getSpeach();
        return view;
    }

    private void getSpeach() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    final TypeWriter textAI = view.findViewById(R.id.text_message_body);
                    textAI.setText("");
                    textAI.setCharacterDelay(50);
                    textAI.animateText("test");
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("ai_replici/CreateProfile/TabHello");
                    ref.limitToFirst(1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<String> raspuns = new ArrayList<>();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                raspuns.add(postSnapshot.getValue().toString());
                                msgDB = postSnapshot.getValue().toString();
                                Log.d("Activitati", TAG+"raspuns dataSnapshot" + raspuns.get(0));
                            }
                            if (msgDB == null) {
                                msgDB = "something did not work ...";
                            }
                            textAI.animateText(msgDB);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Activitati", TAG+"citirea a dat eroare: " + databaseError.getCode());
                        }
                    });
                } catch (final Exception ex) {
                    Log.e("Activitati", TAG+"exceptie in thread: "+ex.getMessage());
                }
            }
        });
    }
}
