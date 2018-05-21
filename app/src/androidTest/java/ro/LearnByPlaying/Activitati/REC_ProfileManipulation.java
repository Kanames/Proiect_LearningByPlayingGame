package ro.LearnByPlaying.Activitati;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
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

import ro.LearnByPLaying.Activitati.ChatBotActivity;
import ro.LearnByPLaying.Activitati.LoginActivity;
import ro.LearnByPLaying.Activitati.ProfileActivity;
import ro.LearnByPLaying.Beans.User;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.android.gms.common.internal.Asserts.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by Stefan on 5/18/2018.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class REC_ProfileManipulation {
    private static String oldNickname = "Kanames";
    private static String newNickname = "Test";
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void ProfileActivityTest() {


        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.editTextEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_textInputLayoutEmail),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("kane@gmail.com"), closeSoftKeyboard());

        //close soft keyboard
        Espresso.closeSoftKeyboard();

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.login_editTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_textInputLayoutPassword),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("titan24xy"), closeSoftKeyboard());

        //close soft keyboard
        Espresso.closeSoftKeyboard();


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login_button), withText("LOGIN to my account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatButton2.perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Kanames")))));

        onView(withId(R.id.recyclerView))
                .check(matches(atPosition(1, hasDescendant(withText("")))));

        onView(withId(R.id.recyclerView))
                .check(matches(atPosition(2, hasDescendant(withText("")))));


//        ViewInteraction textInputEditText20 = onView(
//                allOf(withId(R.id.inputLayoutUpdateValue),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.inputLayoutUpdate),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText20.perform(replaceText("Kanames"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton5 = onView(
//                allOf(withId(R.id.updateBtn), withText("UPDATE"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        1),
//                                1),
//                        isDisplayed()));
//        appCompatButton5.perform(click());

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

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }


}
