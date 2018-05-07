package ro.LearnByPLaying.Activitati.CreatingProfileTabs;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.stefan.proiect_learningbyplayinggame.R;
import ro.LearnByPLaying.Activitati.MainActivity;
import ro.LearnByPLaying.Utilitare.TypeWriter;


/**
 * Created by Stefan on 5/2/2018.
 */

public class TabStart extends Fragment {
    View view;
    public static ImageView img;
    public static TypeWriter textAI;
    public TabStart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.creating_profile_tab_start,container,false);
        FloatingActionButton btnStart = view.findViewById(R.id.CP_floatingActionButton);
        img = view.findViewById(R.id.image_message_profile);
        img.setImageResource(R.drawable.ai_sad);
        final ProgressBar progressBar = view.findViewById(R.id.co_progressBarTabStart);

        textAI = view.findViewById(R.id.text_message_body);
        textAI.setText("");
        textAI.setCharacterDelay(75);
        String msg = "Can't start until you complete your profile creation";
        textAI.animateText(msg);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
            }
        });

        return view;
    }
}
