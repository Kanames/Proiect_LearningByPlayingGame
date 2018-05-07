package ro.LearnByPlaying.Activitati;

import android.support.design.widget.TextInputLayout;
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

import ro.LearnByPLaying.Activitati.ForgotPasswordActivity;
import ro.LearnByPLaying.Activitati.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Stefan on 5/6/2018.
 */

public class ForgotPasswordTest_Espresso {
    @Rule
    public ActivityTestRule<ForgotPasswordActivity> mActivityTestRule = new ActivityTestRule<ForgotPasswordActivity>(ForgotPasswordActivity.class);

    private String realEmailForTest = "test@testEmail.com"; //pass: testtesttest
    private String emptyEmailAddress = "";
    private String fakeEmailAddress = "fakeish@fake.com";

    @Before
    public void setUp() throws Exception{

    }
    @Test
    public void testScenario_01(){
        Log.d("Espresso"," - Scenariul 01 - ");
        Log.d("Espresso","inputs email real: "+realEmailForTest);
        onView(withId(R.id.forgotPass_editTextEmail)).perform(typeText(realEmailForTest));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.forgotPass_button)).perform(click());
    }
    @Test
    public void testScenario_02(){
        Log.d("Espresso"," - Scenariul 02 - ");
        Log.d("Espresso","no inputs");
        //perform button click
        onView(withId(R.id.forgotPass_button)).perform(click());
        onView(withId(R.id.forgotPass_textInputLayoutEmail)).check
                (matches(hasTextInputLayoutErrorText(mActivityTestRule.getActivity().getString(R.string
                        .error_empty,"E-mail"))));
    }
    @Test
    public void testScenario_03(){
        Log.d("Espresso"," - Scenariul 03 - ");
        Log.d("Espresso","fake email: "+fakeEmailAddress);
        onView(withId(R.id.forgotPass_editTextEmail)).perform(typeText(fakeEmailAddress));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.forgotPass_button)).perform(click());
        //TODO verificarea erori ce se afiseaaza pe ecran
    }




    //--- Validate standard setError text set on editText view with Expresso ----
    //---------------------------------------------------------------------------
    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new ForgotPasswordTest_Espresso.ErrorTextMatcher(expectedError);
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

    //--How to test TextInputLayout values (hint, error, etc.) using Android Espresso?---
    //-----------------------------------------------------------------------------------
    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                //CharSequence error = ((TextInputLayout) view).getHint();
                CharSequence error = ((TextInputLayout) view).getError();
                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
    //-----------------------------------------------------------------------------------

    @After
    public void tearDown() throws Exception{

    }
}
