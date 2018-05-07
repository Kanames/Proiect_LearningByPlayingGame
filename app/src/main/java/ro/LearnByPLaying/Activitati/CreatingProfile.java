package ro.LearnByPLaying.Activitati;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.stefan.proiect_learningbyplayinggame.R;

import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabProfile;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabStart;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.TabHello;
import ro.LearnByPLaying.Activitati.CreatingProfileTabs.ViewPageAdapter;
import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.StringUtils;


public class CreatingProfile extends FragmentActivity {
    public static User USER_OBJECT;
    private TabLayout tabLayout;
    public static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);

        tabLayout = findViewById(R.id.CreateProfile_tabLayout);
        viewPager = findViewById(R.id.CreateProfile_viewPager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new TabHello(), "Hello");
        viewPageAdapter.addFragment(new TabProfile(), "Create profile");
        viewPageAdapter.addFragment(new TabStart(), "START");
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            USER_OBJECT = (User) extras.getSerializable("SESSION_USER");
            Log.d("Activitati", "CreatingProfile- USER_OBJECT"+ StringUtils.trfOut(USER_OBJECT));
        }

    }
}
