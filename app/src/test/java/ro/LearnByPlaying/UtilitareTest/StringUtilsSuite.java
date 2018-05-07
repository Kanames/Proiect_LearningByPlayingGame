package ro.LearnByPlaying.UtilitareTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

import ro.LearnByPLaying.Utilitare.StringUtils;
import ro.LearnByPlaying.UtilitareTest.StringUtilsTests.StringUtilsTestCollections;
import ro.LearnByPlaying.UtilitareTest.StringUtilsTests.StringUtilsTestUserObject;

/**
 * Created by Stefan on 4/22/2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        StringUtilsTestCollections.class,
        StringUtilsTestUserObject.class
})
public class StringUtilsSuite{
    // This class remains empty, it is used only as a holder for the above annotations
}
