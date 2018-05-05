package ro.LearnByPlaying.UtilitareTest.DateUtilsTests;

import android.util.Log;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import ro.LearnByPLaying.Utilitare.DateUtils;

import static junit.framework.Assert.assertEquals;


/**
 * Created by Stefan on 5/4/2018.
 */

public class dateObjectTests {
    private static Date currentTime = new Date();

    @Test
    public void displayDateObj() {
        System.out.println("Test 01 - date objects");
        System.out.println(DateUtils.displayDate(currentTime,"dd/mm/yyyy"));
        System.out.println(DateUtils.displayDate(currentTime,"dd-mm-yyyy"));
    }
    @Test
    public void displayTimestampObj() {
        System.out.println("Test 02 - timestamp objects");
        System.out.println(DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd/mm/yyyy"));
        System.out.println(DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd-mm-yyyy"));
    }

    @Test
    public void DateObjTimestampObjEquals() {
        System.out.println("Test 03 - dateObj equals timestampObj");
        String dateObj = DateUtils.displayDate(currentTime,"dd/mm/yyyy");
        String timestampObj =  DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd/mm/yyyy");
        assertEquals(dateObj, timestampObj);
    }


}
