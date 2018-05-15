package ro.LearnByPLaying.Activitati.MainSubActivities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.stefan.proiect_learningbyplayinggame.R;

import ro.LearnByPLaying.Beans.ConfigLessonPanel;


public class LessonActivity extends AppCompatActivity {
    private FloatingActionButton buttonReturn;
    private ImageView wallpaper;
    private ConfigLessonPanel configLessons;
    private TextView textViewLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_lesson_02);
        buttonReturn= findViewById(R.id.butonListMeniu);
        wallpaper = findViewById(R.id.logoLesson);
        textViewLesson = findViewById(R.id.SubTitlu);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d("Activitati", "LessonActivity- "+"< Comming from Main to Lesson >");
            configLessons = (ConfigLessonPanel) extras.getSerializable("CONFIG_LESSON");
        }

        textViewLesson.setText(configLessons.getWallText());
        wallpaper.setImageResource(configLessons.getWallpaper());
        //wallpaper.setBackgroundColor(configLessons.getColorTheme());
        Log.d("Activitati", "LessonActivity- "+"configLessons.getWallText(): "+configLessons.getWallText());
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
