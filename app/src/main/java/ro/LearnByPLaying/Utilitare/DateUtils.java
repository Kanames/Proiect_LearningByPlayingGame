package ro.LearnByPLaying.Utilitare;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Stefan on 5/4/2018.
 */


public class DateUtils {
    /**
     *
     * @param dateToBeDisplayed
     * @param pattern Ex: "MM/dd/yyyy"
     * @return
     */
    public static String displayDate(Date dateToBeDisplayed , String pattern){
        // intialize simpledateformat
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // format the calendar date
        String formattedDate = sdf.format(dateToBeDisplayed.getTime());
        Log.d("Activitati","Formatted date:"+formattedDate);
        return formattedDate;
    }
    public static String displayDate(Timestamp dateToBeDisplayed , String pattern){
        // intialize simpledateformat
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // format the calendar date
        String formattedDate = sdf.format(dateToBeDisplayed);
        Log.d("Activitati","Formatted date:"+formattedDate);
        return formattedDate;
    }

    public static String fromStringToDate(Date dateToBeDisplayed , String pattern){
        // intialize simpledateformat
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // format the calendar date
        String formattedDate = sdf.format(dateToBeDisplayed.getTime());
        Log.d("Activitati","Formatted date:"+formattedDate);
        return formattedDate;
    }
}
