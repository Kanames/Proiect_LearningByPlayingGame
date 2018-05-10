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
        System.out.println("Test 01 - displaying date object: "+currentTime+" in all possible formats");
        System.out.println("'G' Era designator: "+DateUtils.displayDate(currentTime,"y"));
        System.out.println("'M' Month in year: "+DateUtils.displayDate(currentTime,"M"));
        System.out.println("'w' Week in year: "+DateUtils.displayDate(currentTime,"w"));
        System.out.println("'D' Day in year: "+DateUtils.displayDate(currentTime,"D"));
        System.out.println("'k' Hour in day (1-24): "+DateUtils.displayDate(currentTime,"k"));
        System.out.println("'z' Time zone: "+DateUtils.displayDate(currentTime,"z"));
        System.out.println("'dd/MM/yyyy' normal test: "+DateUtils.displayDate(currentTime,"dd/MM/yyyy"));
        System.out.println("'dd-MM-yyyy' normal test: "+DateUtils.displayDate(currentTime,"dd-MM-yyyy"));
    }

    @Test
    public void dateObjEqualsTimestampObj() {
        System.out.println("Test 02 - dateObj equals timestampObj");
        String dateObj = DateUtils.displayDate(currentTime,"dd/mm/yyyy");
        String timestampObj =  DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd/mm/yyyy");
        assertEquals(dateObj, timestampObj);
    }


}
