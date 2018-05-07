package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.stefan.proiect_learningbyplayinggame.R;

import ro.LearnByPLaying.Activitati.Chat_AI.ChatBot;

public class MainActivity extends AppCompatActivity {
    private Toolbar mTopToolbar;
    private LinearLayout linearLayoutLessons;
    private ConstraintLayout main_lesson_line;
    private ImageView chatBotImgView;
    private ImageView imgViewIF,imgViewSwitch;
    private ImageView imgViewFor,imgViewWhile,imgViewDoWhile;
    private ImageView imgViewBreak,imgViewContinue,imgViewReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-- Initializare resurse ---------------------
        mTopToolbar = findViewById(R.id.main_toolbar);
        linearLayoutLessons = findViewById(R.id.main_linearLayoutMainLessons);
        main_lesson_line = findViewById(R.id.main_lesson_line);
        setSupportActionBar(mTopToolbar);
        //---------------------------------------------
        chatBotImgView = findViewById(R.id.main_imgViewChatBot);

        chatBotImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatBot.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            Log.d("Activitati", "MainActivity- " + "menu profile");
            Toast.makeText(MainActivity.this, "Action menu_profile", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menu_about) {
            Log.d("Activitati", "MainActivity- " + "menu about");
            Toast.makeText(MainActivity.this, "Action menu_about", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.log_out) {
            Log.d("Activitati", "MainActivity- " + "log out");
            Toast.makeText(MainActivity.this, "Application closed", Toast.LENGTH_SHORT).show();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
