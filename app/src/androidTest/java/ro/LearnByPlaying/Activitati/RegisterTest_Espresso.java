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

import ro.LearnByPLaying.Activitati.LoginActivity;
import ro.LearnByPLaying.Activitati.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;

/**
 * Created by Stefan on 5/6/2018.
 */

public class RegisterTest_Espresso {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    private String emailAlreadyRegistered = "kane@gmail.com";
    private String password1AlreadyRegistered = "titan24xy";
    private String password2AlreadyRegistered = "titan24xy";

    private String password1TooSmall = "p";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testScenario_01(){
        Log.d("Espresso"," - Scenariul 01 3 input-uri bune dar fara sa dam checkBox yes - ");
        Log.d("Espresso","inputs email: "+emailAlreadyRegistered);
        Log.d("Espresso","inputs password: "+password1AlreadyRegistered);
        Log.d("Espresso","inputs password: "+password2AlreadyRegistered);
        //input some text in edit text
        onView(withId(R.id.Register_editTextEmail)).perform(typeText(emailAlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass1)).perform(typeText(password1AlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass2)).perform(typeText(password2AlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.Register_btnRegister)).perform(click());
        //checking the toast
        RegisterActivity activity = mActivityTestRule.getActivity();
        onView(withText("Please agree with the terms")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testScenario_02(){
        Log.d("Espresso"," - Scenariul 02 doar email | checkBox NO - ");
        Log.d("Espresso","inputs email: "+emailAlreadyRegistered);
        Log.d("Espresso","inputs password: "+password1AlreadyRegistered);
        Log.d("Espresso","inputs password: "+password2AlreadyRegistered);
        //input some text in edit text
        onView(withId(R.id.Register_editTextEmail)).perform(typeText(emailAlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.Register_btnRegister)).perform(click());
    }

    @Test
    public void testScenario_03(){
        Log.d("Espresso"," - Scenariul 03 doar email | parola 1 prea mica - ");
        Log.d("Espresso","inputs email: "+emailAlreadyRegistered);
        Log.d("Espresso","inputs password: "+password1TooSmall);
        //input some text in edit text
        onView(withId(R.id.Register_editTextEmail)).perform(typeText(emailAlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass1)).perform(typeText(password1TooSmall));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.Register_btnRegister)).perform(click());

        onView(withId(R.id.Register_textInputLayoutPass1)).check
                (matches(hasTextInputLayoutErrorText(mActivityTestRule.getActivity().getString(R.string
                        .error_weak_password,8))));
    }

    @Test
    public void testScenario_04(){
        Log.d("Espresso"," - Scenariul 04 doar email - ");
        Log.d("Espresso","inputs email: "+emailAlreadyRegistered);
        //input some text in edit text
        onView(withId(R.id.Register_editTextEmail)).perform(typeText(emailAlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.Register_btnRegister)).perform(click());

        onView(withId(R.id.Register_textInputLayoutPass1)).check
                (matches(hasTextInputLayoutErrorText(mActivityTestRule.getActivity().getString(R.string
                        .error_empty,"password"))));
    }

    @Test
    public void testScenario_05(){
        Log.d("Espresso"," - Scenariul 05 doar cele 2 parole introduse - ");
        Log.d("Espresso","inputs pass1: "+password1AlreadyRegistered);
        Log.d("Espresso","inputs pass2: "+password2AlreadyRegistered);
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass1)).perform(typeText(password1AlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass2)).perform(typeText(password2AlreadyRegistered));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        onView(withId(R.id.Register_btnRegister)).perform(click());
        //checking the text in the textView
        Espresso.onView(withId(R.id.Register_editTextEmail)).check(matches(hasErrorText("Error: please fill the input E-mail")));
    }






    //--- Validate standard setError text set on editText view with Expresso ------------
    //-----------------------------------------------------------------------------------
    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new RegisterTest_Espresso.ErrorTextMatcher(expectedError);
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
    //-----------------------------------------------------------------------------------

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
