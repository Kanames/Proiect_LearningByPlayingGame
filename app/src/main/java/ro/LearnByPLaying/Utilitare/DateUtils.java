package ro.LearnByPLaying.Utilitare;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Stefan on 5/4/2018.
 */


public class DateUtils {
    /**
     * @param dateTypeObject The object of type Date that will be formated.
     * @param pattern The object of type String that will represents the pattern in witch the object Date with be displayed Ex: "MM/dd/yyyy"
     * @return A object of type String that represents the Date object in human readable format.
     */
    public static String displayDate(Date dateTypeObject , String pattern){
        Log.d("Activitati","DateUtils.displayDate("+dateTypeObject+","+pattern+")");
        // Initializare SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // format the calendar date
        String formattedDate = sdf.format(dateTypeObject.getTime());
        Log.d("Activitati","Formatted date:"+formattedDate);
        return formattedDate;
    }

    public static String displayDate(Timestamp dateToBeDisplayed , String pattern){
        // Initializare SimpleDateFormat
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

    public static Timestamp fromDateToTimestamp(Date dateToBeTransformed){
        return new Timestamp(dateToBeTransformed.getTime());
    }
    public static Timestamp fromCalendarToTimestamp(Calendar dateToBeTransformed){
        return new Timestamp(dateToBeTransformed.getTimeInMillis());
    }

    public static HashMap<String,Long> differenceBetweenTwoDates(Date dateStart, Date dateEnd){
        HashMap<String,Long> mapDiff = new HashMap<>();
        long dif= dateEnd.getTime() - dateStart.getTime();
        long difSeconds = dif / 1000 % 60;
        mapDiff.put("seconds",difSeconds);
        long difMinutes = dif / (60 * 1000) % 60;
        mapDiff.put("minutes",difMinutes);
        long difHours = dif / (60 * 60 * 1000);
        mapDiff.put("hours",difHours);
        long difInDays = (int) ((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24));
        mapDiff.put("days",difInDays);
        return mapDiff;
    }
}
