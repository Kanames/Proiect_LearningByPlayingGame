package ro.LearnByPlaying.Activitati;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.stefan.proiect_learningbyplayinggame.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ro.LearnByPLaying.Activitati.LoginActivity;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Stefan on 5/6/2018.
 */

public class LoginTest_Espresso {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private String email = "kane@gmail.com";
    private String password = "titan24xy";


    @Before
    public void setUp() throws Exception{

    }
    @Test
    public void testScenario_01(){
        Log.d("Espresso"," - Scenariul 01 - ");
        Log.d("Espresso","inputs email: "+email);
        Log.d("Espresso","inputs password: "+password);
        //input some text in edit text
        onView(withId(R.id.editTextEmail)).perform(typeText(email));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.login_editTextPassword)).perform(typeText(password));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login_button)).perform(click());
        //checking the text in the textView
        //Espresso.onView(withId(R.id._)).perform(matches(withText()));
    }
    @Test
    public void testScenario_02(){
        Log.d("Espresso"," - Scenariul 02 - ");
        Log.d("Espresso","inputs password: "+password);
        onView(withId(R.id.login_editTextPassword)).perform(typeText(password));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login_button)).perform(click());
        //checking the text in the textView
        Espresso.onView(withId(R.id.editTextEmail)).check(matches(hasErrorText("Please enter your e-mail")));
    }
    @Test
    public void testScenario_03(){
        Log.d("Espresso"," - Scenariul 03 - ");
        Log.d("Espresso","inputs email: "+email);
        //input some text in edit text
        onView(withId(R.id.editTextEmail)).perform(typeText(email));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.login_button)).perform(click());
        //checking the text in the textView
    }


    //--- Validate standard setError text set on editText view with Expresso ----
    //---------------------------------------------------------------------------
    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new ErrorTextMatcher(expectedError);
    }
    private static class ErrorTextMatcher extends TypeSafeMatcher<View> {
        private final String expectedError;

        private ErrorTextMatcher(String expectedError) {
            this.expectedError = checkNotNull(expectedError);
        }

        @Override
        public boolean matchesSafely(View view) {
            if (!(view instanceof EditText)) {
                return false;
            }
            EditText editText = (EditText) view;
            return expectedError.equals(editText.getError());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error: " + expectedError);
        }
    }
    //---------------------------------------------------------------------------

    @After
    public void tearDown() throws Exception{

    }
}
