package ro.LearnByPLaying.Utilitare;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Stefan on 4/18/2018.
 */

public class StringUtils {
    public static String trfOut(Object object){
        Log.d("StringUtils","<<< IN trsfOut() >>>");
        StringBuffer representation = new StringBuffer();
        representation.append(object.getClass().getName());
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object value;
                value = field.get(object);
                representation.append(" [" + name + ":" + value + "] ");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        Log.d("StringUtils","<<< OUT trsfOut() >>>");
        return representation.toString();
    }
}
