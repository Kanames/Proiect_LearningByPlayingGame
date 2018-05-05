package ro.LearnByPLaying.Activitati;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.stefan.proiect_learningbyplayinggame.R;

import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabProfile;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabStart;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabHello;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.ViewPageAdapter;
import ro.LearnByPLaying.Beans.User;


public class CreatingProfile extends FragmentActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    public static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);

        tabLayout = findViewById(R.id.CreateProfile_tabLayout);
        appBarLayout = findViewById(R.id.CreateProfile_appBar);
        viewPager = findViewById(R.id.CreateProfile_viewPager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new TabHello(), "Hello");
        viewPageAdapter.addFragment(new TabProfile(), "Create profile");
        viewPageAdapter.addFragment(new TabStart(), "START");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d("Activitati", "We have extras in Create profile toooo X_X !!!");
            User userObject = (User) extras.getSerializable("SESSION_USER");
            Log.d("Activitati", userObject.getUserFirebaseID());
        }

    }
}
