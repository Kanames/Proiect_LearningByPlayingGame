package ro.LearnByPlaying.UtilitareTest.DateUtilsTests;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import ro.LearnByPLaying.Utilitare.DateUtils;

/**
 * Created by Stefan on 5/5/2018.
 */

public class timestampObjectTests {
    private static Date currentTime = new Date();

    @Test
    public void displayTimestampObj() {
        System.out.println("Test 01 - timestamp objects");
        System.out.println(DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd/mm/yyyy"));
        System.out.println(DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd-mm-yyyy"));
    }

}
