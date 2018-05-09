package ro.LearnByPLaying.Activitati.MainSubActivities;

import android.content.Intent;
import android.view.View;

import java.security.AccessController;

import ro.LearnByPLaying.Activitati.MainActivity;


/**
 * Created by Stefan on 5/9/2018.
 */

public class LessonOnClickListener implements View.OnClickListener
{

    ConfigLessons configLessons;
    public LessonOnClickListener(ConfigLessons configLessons) {
        this.configLessons = configLessons;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(v.getContext(), LessonActivity.class);
        intent.putExtra("CONFIG_LESSON", configLessons);
        v.getContext().startActivity(intent);
    }

};
