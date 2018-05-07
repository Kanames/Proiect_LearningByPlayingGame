package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    View view;
    private static String msgDB;
    public TabHello() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.creating_profile_tab_hello,container,false);
        TextView editTextTitle = view.findViewById(R.id.CP_HelloAboutAPPTitle);
        editTextTitle.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPPTitle)));
        TextView editText = view.findViewById(R.id.CP_HelloAboutAPP);
        editText.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPP)));
        TextView editTextJava = view.findViewById(R.id.CP_HelloAboutAPPJava);
        editTextJava.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPPJava)));
        TextView editTextsql = view.findViewById(R.id.CP_HelloAboutAPPsql);
        editTextsql.setText(Html.fromHtml(getString(R.string.CP_HelloAboutAPPsql)));
        getSpeach();

        return view;
    }
    private void getSpeach() {
        getActivity().runOnUiThread(new Runnable(){
            public void run() {
                try {
                    final TypeWriter textAI = view.findViewById(R.id.text_message_body);
                    textAI.setText("");
                    textAI.setCharacterDelay(50);
                    textAI.animateText("test");
                    // Get a reference to our posts
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("ai_replici/CreateProfile/TabHello");
                    // Attach a listener to read the data at our posts reference
                    ref.limitToFirst(1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<String> raspuns = new ArrayList<>();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                raspuns.add(postSnapshot.getValue().toString());
                                msgDB = postSnapshot.getValue().toString();
                                Log.d("Activitati","R------"+raspuns.get(0));
                            }
                            if(msgDB==null){
                                msgDB="ceva nu a mers";
                            }
                            textAI.animateText(msgDB);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Activitati","The read failed: " + databaseError.getCode());
                        }
                    });
                } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                }
            }
        });
    }
}
