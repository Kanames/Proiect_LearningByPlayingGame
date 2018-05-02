package ro.LearnByPLaying.Utilitare;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ro.LearnByPLaying.Exception.RegisterException;

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
