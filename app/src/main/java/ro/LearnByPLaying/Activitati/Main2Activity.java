package ro.LearnByPLaying.Activitati;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.stefan.proiect_learningbyplayinggame.R;

import java.util.ArrayList;

import ro.LearnByPLaying.Beans.Courses;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = findViewById(R.id.button);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ArrayList<Courses> cursuri = FirebaseRealtimeDBUtils.getCoursesList();
        Log.d("Activitati","=--=-"+cursuri.size());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(cursuri.get(0).getTempLinkBackgroundImg()));
            }
        });



    }
}
