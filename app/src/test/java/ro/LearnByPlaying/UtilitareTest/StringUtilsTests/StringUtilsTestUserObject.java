package ro.LearnByPlaying.UtilitareTest.StringUtilsTests;

import org.junit.Test;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.StringUtils;

/**
 * Created by Stefan on 5/7/2018.
 */

public class StringUtilsTestUserObject {
    private User user = new User("test@test.com");

    @Test
    public void humanReadableObjects() {
        System.out.println(StringUtils.trfOut(user));
    }

    @Test
    public void humanReadableObjects_betterMethod() {
        System.out.println(StringUtils.readObject(user));
    }
}
