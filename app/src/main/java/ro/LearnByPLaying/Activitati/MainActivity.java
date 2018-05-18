package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.stefan.proiect_learningbyplayinggame.R;

import java.util.ArrayList;

import ro.LearnByPLaying.Beans.ConfigLessonPanel;
import ro.LearnByPLaying.Activitati.LessonActivity.menuListLessonOnClickListener;
import ro.LearnByPLaying.Adapters.RecyclerViewLessonsAdapter;
import ro.LearnByPLaying.Beans.LessonsList;
import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.FunctionalMethods;

import static ro.LearnByPLaying.Utilitare.StringUtils.readObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity- ";
    private static ArrayList<ConfigLessonPanel> listLessons;
    private static Toolbar mTopToolbar;
    public static  User SESSION_USER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-- Initializare resurse ---------------------
         mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        SESSION_USER = (User) FunctionalMethods.methodGetUser(getIntent().getExtras(),"SESSION_USER","GET");
        //---------------------------------------------



        Log.d("Activitati",TAG+"the user object we got from session: "+readObject(SESSION_USER));

        View.OnClickListener chatBotClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
                intent.putExtra("SESSION_USER", SESSION_USER);
                startActivity(intent);
            }
        };


        final RecyclerView recyclerView = findViewById(R.id.main_list_lessons);

        ArrayList<String> titlesLesssons = new ArrayList<>();
        ArrayList<Integer> titlesLesssonsIcons = new ArrayList<>();
        ArrayList<View.OnClickListener> onClickListeners = new ArrayList<>();
        ArrayList<Integer> backColoors = new ArrayList<>();

        titlesLesssons.add("Choose your career  with Ben");
        titlesLesssonsIcons.add(R.drawable.icon_chat_box);
        onClickListeners.add(chatBotClickListener);
        backColoors.add(Color.parseColor("#F57C00"));

        titlesLesssons.add("Introduction");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_3);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_3,Color.parseColor("#e57373"),getString(R.string.lesson_functional_programming),"Introduction")));
        backColoors.add(Color.parseColor("#e57373"));

        titlesLesssons.add("F.P. (Functional Programming)");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_2);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_2,Color.parseColor("#ba68c8"),getString(R.string.lesson_pure_functions),"Basic theory F.P.")));
        backColoors.add(Color.parseColor("#ba68c8"));

        titlesLesssons.add("F.P. (Functional Programming)");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_1);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_1,Color.parseColor("#7986cb"),getString(R.string.lesson_fclass_hordefunctions),"Basic theory F.P.")));
        backColoors.add(Color.parseColor("#7986cb"));

        titlesLesssons.add("F.P. (Functional Programming)");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_4);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_4,Color.parseColor("#4fc3f7"),getString(R.string.lesson_recursion),"Basic theory F.P.")));
        backColoors.add(Color.parseColor("#4fc3f7"));

        titlesLesssons.add("F.P. (Functional Programming)");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_5);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_5,Color.parseColor("#4db6ac"),getString(R.string.lesson_lambdas),"Basic theory F.P.")));
        backColoors.add(Color.parseColor("#4db6ac"));

        titlesLesssons.add("Programming concepts");
        titlesLesssonsIcons.add(R.drawable.functional_programing_icons_6);
        onClickListeners.add(new menuListLessonOnClickListener(new ConfigLessonPanel(R.drawable.functional_programing_icons_6,Color.parseColor("#aed581"),getString(R.string.lesson_oop_vs_fp),"Basic concepts F.P.")));
        backColoors.add(Color.parseColor("#aed581"));

        //a 4 coloana https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=FF8A65
        //fff176
        //ffb74d
        //ff8a65
        //e0e0e0

        LessonsList lessonsList = new LessonsList(titlesLesssons,titlesLesssonsIcons,onClickListeners,backColoors);

        final RecyclerViewLessonsAdapter adapter = new RecyclerViewLessonsAdapter(lessonsList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




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
            Intent intentMenuProfile = new Intent(MainActivity.this, ProfileActivity.class);
            intentMenuProfile.putExtra("SESSION_USER",SESSION_USER);
            startActivity(intentMenuProfile);
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
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
