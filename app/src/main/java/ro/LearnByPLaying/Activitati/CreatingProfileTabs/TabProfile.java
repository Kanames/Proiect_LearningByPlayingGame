package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;

import org.w3c.dom.Text;

import ro.LearnByPLaying.Activitati.CreatingProfile;

/**
 * Created by Stefan on 5/2/2018.
 */

public class TabProfile extends Fragment {
    public static Boolean IS_PROFILE_CREATED;

    View view;
    public static Button btnProfile;
    private TextView displayTextView;
    private ViewPager viewPager;
    private EditText nickname,firstname,lastname,country;

    public TabProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.creating_profile_tab_profile, container, false);
        displayTextView = view.findViewById(R.id.CP_editText);
        btnProfile = view.findViewById(R.id.CP_btnCompleteProfile);
        viewPager = view.findViewById(R.id.CreateProfile_viewPager);

        displayTextView.setText(Html.fromHtml(getString(R.string.CP_StringProfileBuilder)));

        Spinner spinner = (Spinner) view.findViewById(R.id.CP_countrySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.conuntrys_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                CreatingProfile.viewPager.setCurrentItem(2);
            }
        });


        return view;
    }

}
