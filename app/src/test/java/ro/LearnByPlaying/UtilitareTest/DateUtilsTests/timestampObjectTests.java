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
        System.out.println("Test 01 - displaying timestamp object: "+new Timestamp(currentTime.getTime())+" in all possible formats");
        System.out.println("'G' Era designator: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"y"));
        System.out.println("'M' Month in year: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"M"));
        System.out.println("'w' Week in year: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"w"));
        System.out.println("'D' Day in year: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"D"));
        System.out.println("'k' Hour in day (1-24): "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"k"));
        System.out.println("'z' Time zone: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"z"));
        System.out.println("'dd/MM/yyyy' normal test: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd/MM/yyyy"));
        System.out.println("'dd-MM-yyyy' normal test: "+DateUtils.displayDate(new Timestamp(currentTime.getTime()),"dd-MM-yyyy"));
    }

}
