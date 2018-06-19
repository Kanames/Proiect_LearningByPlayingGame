package ro.LearnByPlaying.Activitati;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.stefan.proiect_learningbyplayinggame.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import ro.LearnByPLaying.Activitati.ChatBotActivity;
import ro.LearnByPLaying.Beans.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Stefan on 5/15/2018.
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class ChatbotTest_Espresso {
    public static final ArrayList<String> questions = new ArrayList<>();

    private void init() {
        questions.add("Hi");
        questions.add("Hi      ");
        questions.add("        Hi");
        questions.add("              Hi                ");
        questions.add("123");
        questions.add("");
        questions.add("------------------");
        questions.add("????");
        questions.add("@$$%");

    }

    @Rule
    public ActivityTestRule<ChatBotActivity> mActivityRule = new ActivityTestRule<ChatBotActivity>(ChatBotActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, ChatBotActivity.class);
            User userObj = new User();
            userObj.setNickName("Espresso testing");
            result.putExtra("SESSION_USER", userObj);
            return result;
        }
    };

    @Test
    public void someTest() {
        init();
        for(int i = 0 ; i < questions.size() ; i ++){
            //input some text in edit text
            onView(withId(R.id.input)).perform(typeText(questions.get(i)));
            //close soft keyboard
            Espresso.closeSoftKeyboard();
            //perform button click
            onView(withId(R.id.fab)).perform(click());
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                Log.e("Espresso",e.getMessage());
            }
        }
    }



}
