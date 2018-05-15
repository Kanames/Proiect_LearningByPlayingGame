package ro.LearnByPLaying.Activitati.MainSubActivities;

import android.content.Intent;
import android.view.View;

import ro.LearnByPLaying.Beans.ConfigLessonPanel;


/**
 * Created by Stefan on 5/9/2018.
 */

public class menuListLessonOnClickListener implements View.OnClickListener
{

    ConfigLessonPanel configLessons;
    public menuListLessonOnClickListener(ConfigLessonPanel configLessons) {
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
