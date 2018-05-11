package ro.LearnByPLaying.Activitati.MainSubActivities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.stefan.proiect_learningbyplayinggame.R;


public class LessonActivity extends AppCompatActivity {
    private FloatingActionButton buttonReturn;
    private ImageView wallpaper;
    private  ConfigLessons configLessons;
    private TextView textViewLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        buttonReturn= findViewById(R.id.lesson_buttonReturn);
        wallpaper = findViewById(R.id.lesson_wallpaper);
        textViewLesson = findViewById(R.id.lesson_textViewLesson);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d("Activitati", "LessonActivity- "+"< Comming from Main to Lesson >");
            configLessons = (ConfigLessons) extras.getSerializable("CONFIG_LESSON");
        }

        textViewLesson.setText(configLessons.getWallText());
        wallpaper.setImageResource(configLessons.getWallpaper());
        wallpaper.setBackgroundColor(configLessons.getColorTheme());
        Log.d("Activitati", "LessonActivity- "+"configLessons.getWallText(): "+configLessons.getWallText());
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
