package ro.LearnByPlaying.UtilitareTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ro.LearnByPlaying.UtilitareTest.DateUtilsTests.dateObjectTests;
import ro.LearnByPlaying.UtilitareTest.DateUtilsTests.timestampObjectTests;

/**
 * Created by Stefan on 5/4/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        dateObjectTests.class,
        timestampObjectTests.class
})
public class DateUtilsSuite {
}
