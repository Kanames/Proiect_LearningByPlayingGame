package ro.LearnByPLaying.Activitati.CreatingProfileTabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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

import java.util.HashMap;

import ro.LearnByPLaying.Activitati.CreatingProfileActivity;
import ro.LearnByPLaying.Utilitare.FirebaseRealtimeDBUtils;
import ro.LearnByPLaying.Utilitare.StringUtils;

import static ro.LearnByPLaying.Activitati.CreatingProfileActivity.USER_OBJECT;

/**
 * Created by Stefan on 5/2/2018.
 */

public class TabProfile extends Fragment {
    private static final String TAG = "Fragment-TabProfile- ";
    View view;
    public static Button btnProfile;
    private Spinner dropListCountry;
    private TextView displayTextView;
    private EditText nickname, firstname, lastname;
    private TextInputLayout textInputLayoutNickname, textInputLayoutFirstname, textInputLayoutLastname;
    private View viewIncludeNickname,viewIncludeFirstname,viewIncludeLastname,viewIncludeCountry;
    public TabProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("Activitati", "<<< Intrat in TabProfile >>>");
        view = inflater.inflate(R.layout.creating_profile_tab_profile, container, false);
        displayTextView = view.findViewById(R.id.CP_editText);
        btnProfile = view.findViewById(R.id.CP_btnCompleteProfile);

        viewIncludeNickname  = view.findViewById(R.id.customConstraintLayoutNickname);
        viewIncludeFirstname = view.findViewById(R.id.customConstraintLayoutFirstname);
        viewIncludeLastname  = view.findViewById(R.id.customConstraintLayoutLastname);
        viewIncludeCountry  = view.findViewById(R.id.customConstraintLayoutCountry);

        textInputLayoutNickname   = (TextInputLayout) viewIncludeNickname.findViewById(R.id.textDisplayed);
        textInputLayoutFirstname  = (TextInputLayout) viewIncludeFirstname.findViewById(R.id.textDisplayed);
        textInputLayoutLastname   = (TextInputLayout) viewIncludeLastname.findViewById(R.id.textDisplayed);

        nickname   = (EditText) viewIncludeNickname.findViewById(R.id.inputValue);
        firstname  = (EditText) viewIncludeFirstname.findViewById(R.id.inputValue);
        lastname   = (EditText) viewIncludeLastname.findViewById(R.id.inputValue);

        textInputLayoutNickname.setHint(getString(R.string.hint_nickname)+getString(R.string.hint_needed));
        textInputLayoutFirstname.setHint(getString(R.string.hint_first_name)+getString(R.string.hint_needed));
        textInputLayoutLastname.setHint(getString(R.string.hint_last_name)+getString(R.string.hint_optional));

        TextView dropListCountryDescription = (TextView) viewIncludeCountry.findViewById(R.id.descriptionDropList);
        dropListCountryDescription.setText(getString(R.string.hint_country)+getString(R.string.hint_optional));
        dropListCountry = (Spinner) viewIncludeCountry.findViewById(R.id.dropListValues);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.conuntrys_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropListCountry.setAdapter(adapter);

        displayTextView.setText(Html.fromHtml(getString(R.string.CP_StringProfileBuilder)));

        btnProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    StringUtils.controlTextInput(nickname, textInputLayoutNickname, getString(R.string.error_empty, "Nickname"));
                    StringUtils.controlTextInput(firstname, textInputLayoutFirstname, getString(R.string.error_empty, "Firstname"));
                    Log.d("Activitati", TAG+"Check nickname: " + nickname.getText().toString());
                    Log.d("Activitati", TAG+"Check firstname: " + firstname.getText().toString());
                    Log.d("Activitati", TAG+"Check lastname: " + lastname.getText().toString());
                    Log.d("Activitati", TAG+"Check country: " + dropListCountry.getSelectedItem().toString());

                    USER_OBJECT.setNickName(nickname.getText().toString());
                    USER_OBJECT.setFirstName(firstname.getText().toString());
                    USER_OBJECT.setLastName(lastname.getText().toString());
                    USER_OBJECT.setCountry(dropListCountry.getSelectedItem().toString());

                    HashMap toModify = new HashMap();
                    toModify.put("nickName", USER_OBJECT.getNickName());
                    toModify.put("country", USER_OBJECT.getCountry());
                    toModify.put("firstname", USER_OBJECT.getFirstName());
                    toModify.put("lastname", USER_OBJECT.getLastName());

                    new taskUpdateUser().execute(toModify);

                    TabStart.img.setImageResource(R.drawable.ai_happy);
                    TabStart.textAI.setCharacterDelay(75);
                    TabStart.textAI.animateText("Yey, you completed your profile and I am very glad to meet you " + USER_OBJECT.getNickName() + " we can start the app now by clicking the rounded button at the bottom of this activity");
                    CreatingProfileActivity.viewPager.post(new Runnable() {
                        @Override
                        public void run() {
                            CreatingProfileActivity.viewPager.setCurrentItem(2);
                        }
                    });

                } catch (Exception e) {
                    Log.e("Activitati", TAG+"Eroare: " + e.getMessage());
                }
            }
        });
        return view;
    }

    private static class taskUpdateUser extends AsyncTask<HashMap,Void,Void>{
        @Override
        protected Void doInBackground(HashMap... hashMaps) {
            FirebaseRealtimeDBUtils.updateUSER(USER_OBJECT, hashMaps[0]);
            return null;
        }
    }
}
