package ro.LearnByPlaying.Activitati;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.stefan.proiect_learningbyplayinggame.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.LearnByPLaying.Activitati.LoginActivity;
import ro.LearnByPLaying.Activitati.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class REC_RegisteredFlowing {

    private static final String emailDeInregistrat  = "testApp@gmail.com";
    private static final String parolaDeInregistrat = "12345678";
    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void loginActivityTest() {

        //input some text in edit text
        onView(withId(R.id.Register_editTextEmail)).perform(typeText(emailDeInregistrat));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass1)).perform(typeText(parolaDeInregistrat));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.Register_editTextPass2)).perform(typeText(parolaDeInregistrat));
        //close soft keyboard
        Espresso.closeSoftKeyboard();

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.Register_checkBoxAgree), withText("Check if you accept terms and conditions"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatCheckBox.perform(click());



        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register_btnAgreementAcceptedYES), withText("I agree with the terms and conditions"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayoutAgreement),
                                        childAtPosition(
                                                withId(R.id.scrollAgreementDetails),
                                                0)),
                                1)));
        appCompatButton.perform(scrollTo(), click());


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.Register_btnRegister), withText("Register to our system"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        //input some text in edit text
        onView(withId(R.id.editTextEmail)).perform(typeText(emailDeInregistrat));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //input some text in edit text
        onView(withId(R.id.login_editTextPassword)).perform(typeText(parolaDeInregistrat));
        //close soft keyboard
        Espresso.closeSoftKeyboard();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.login_button), withText("LOGIN to my account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatButton3.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
