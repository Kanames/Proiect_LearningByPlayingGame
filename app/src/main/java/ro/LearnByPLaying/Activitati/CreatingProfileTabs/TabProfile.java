package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
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

import java.util.HashMap;

import ro.LearnByPLaying.Activitati.CreatingProfile;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;
import ro.LearnByPLaying.Utilitare.StringUtils;

import static ro.LearnByPLaying.Activitati.CreatingProfile.USER_OBJECT;

/**
 * Created by Stefan on 5/2/2018.
 */

public class TabProfile extends Fragment {
    View view;
    public static Button btnProfile;
    Spinner spinner;
    private TextView displayTextView;
    private EditText nickname, firstname, lastname;
    private TextInputLayout textInputLayoutNickname, textInputLayoutFirstname, textInputLayoutLastname;

    public TabProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.creating_profile_tab_profile, container, false);
        displayTextView = view.findViewById(R.id.CP_editText);
        btnProfile = view.findViewById(R.id.CP_btnCompleteProfile);
        nickname = view.findViewById(R.id.CP_textEditNickname);
        firstname = view.findViewById(R.id.CP_textEditFirstName);
        lastname = view.findViewById(R.id.CP_textEditLastName);
        textInputLayoutNickname = view.findViewById(R.id.CP_TextInputLayoutNickname);
        textInputLayoutFirstname = view.findViewById(R.id.CP_TextInputLayoutFirstName);
        textInputLayoutLastname = view.findViewById(R.id.CP_TextInputLayoutLastName);

        displayTextView.setText(Html.fromHtml(getString(R.string.CP_StringProfileBuilder)));

        spinner = (Spinner) view.findViewById(R.id.CP_countrySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.conuntrys_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    StringUtils.controlTextInput(nickname, textInputLayoutNickname, getString(R.string.error_empty, "Nickname"));
                    StringUtils.controlTextInput(firstname, textInputLayoutFirstname, getString(R.string.error_empty, "Firstname"));
                    Log.d("Activitati", "Check nickname: " + nickname.getText().toString());
                    Log.d("Activitati", "Check firstname: " + firstname.getText().toString());
                    Log.d("Activitati", "Check lastname: " + lastname.getText().toString());
                    Log.d("Activitati", "Check country: " + spinner.getSelectedItem().toString());

                    USER_OBJECT.setNickName(nickname.getText().toString());
                    USER_OBJECT.setFirstName(firstname.getText().toString());
                    USER_OBJECT.setLastName(lastname.getText().toString());
                    USER_OBJECT.setCountry(spinner.getSelectedItem().toString());

                    HashMap toModify = new HashMap();
                    toModify.put("nickName", USER_OBJECT.getNickName());
                    toModify.put("country", USER_OBJECT.getCountry());
                    toModify.put("firstname", USER_OBJECT.getFirstName());
                    toModify.put("lastname", USER_OBJECT.getLastName());
                    FirebaseRealtimeDBUtils.updateUSER(USER_OBJECT, toModify);
                    TabStart.img.setImageResource(R.drawable.ai_happy);
                    TabStart.textAI.setCharacterDelay(75);
                    TabStart.textAI.animateText("Yey, you completed your profile and I am very glad to meet you " + USER_OBJECT.getNickName() + " we can start the app now by clicking the rounded button at the bottom of this activity");
                    CreatingProfile.viewPager.post(new Runnable() {
                        @Override
                        public void run() {
                            CreatingProfile.viewPager.setCurrentItem(2);
                        }
                    });

                } catch (Exception e) {
                    Log.e("Activitati", "Eroare: " + e.getMessage());
                }
            }
        });
        return view;
    }

}
