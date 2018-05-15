package ro.LearnByPLaying.Activitati.CreatingProfileTabs;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.stefan.proiect_learningbyplayinggame.R;
import ro.LearnByPLaying.Activitati.MainActivity;
import ro.LearnByPLaying.Utilitare.TypeWriter;

import static ro.LearnByPLaying.Activitati.CreatingProfileActivity.USER_OBJECT;


/**
 * Created by Stefan on 5/2/2018.
 */

public class TabStart extends Fragment {
    private static final String TAG = "Fragment-TabStart- ";
    public static View view;
    public static ImageView img;
    public static TypeWriter textAI;
    private static FloatingActionButton btnStart;
    public TabStart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("Activitati", "<<< Intrat in TabStart >>>");
        view     = inflater.inflate(R.layout.creating_profile_tab_start,container,false);
        btnStart = view.findViewById(R.id.CP_floatingActionButton);
        img      = view.findViewById(R.id.image_message_profile);

        img.setImageResource(R.drawable.ai_sad);


        textAI = view.findViewById(R.id.text_message_body);
        textAI.setText("");
        textAI.setCharacterDelay(75);
        String msg = "Can't start until you complete your profile creation";
        textAI.animateText(msg);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if(USER_OBJECT.getNickName() != null && USER_OBJECT.getNickName() !="") {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("SESSION_USER", USER_OBJECT);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        textAI.animateText("You can press for as long as you want but until you complete the creation of your profile we won't go foward");

                    }
            }
        });

        return view;
    }
}
