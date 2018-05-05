package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;

import ro.LearnByPLaying.Activitati.CreatingProfile;
import ro.LearnByPLaying.Activitati.LoginActivity;
import ro.LearnByPLaying.Activitati.MainActivity;

/**
 * Created by Stefan on 5/2/2018.
 */

public class TabStart extends Fragment {
    View view;
    public TabStart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.creating_profile_tab_start,container,false);
        FloatingActionButton btnStart = view.findViewById(R.id.CP_floatingActionButton);
        final ProgressBar progressBar = view.findViewById(R.id.co_progressBarTabStart);



        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //startActivity(intent);
                //getActivity().finish();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}
