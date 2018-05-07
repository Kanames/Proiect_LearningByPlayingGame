package ro.LearnByPLaying.Utilitare;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.security.auth.login.LoginException;

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

    public static String readObject(Object object){
        Log.d("StringUtils","<<< IN trsfOut() >>>");
        if(object == null){
            return null;
        }
        if(object instanceof String){
            return (String) object;
        }
        if(object instanceof Date){
            return object ==null ? null : object.toString();
        }
        if(object instanceof Number){
            return object ==null ? null : object.toString();
        }
        if(object instanceof Map){
            final StringBuffer representation = new StringBuffer("[");
            Map map = (Map) object;
            if(!map.isEmpty()){
                for(final Object item: map.keySet()){
                    representation.append(item).append("=").append(readObject(map.get(item))).append(", ");
                }
            }
            representation.append("]");
            return object ==null ? null : object.toString();
        }
        if(object instanceof EditText){
            return object.toString();
        }
        final StringBuffer representation = new StringBuffer("[");
        for(final Object item : (Collection) object){
            representation.append(readObject(item)).append(", ");
        }
        representation.append("]");
        Log.d("StringUtils","<<< OUT trsfOut() >>>");
        return representation.toString();
    }


    public static void controlTextInput(EditText editText, String errorEmpty) throws Exception{
        String rawEditTextValue = editText.getText().toString();
        if (TextUtils.isEmpty(rawEditTextValue)) {
            editText.requestFocus();
            editText.setError(errorEmpty);
            throw new Exception(errorEmpty);
        }
    }
    public static void controlTextInput(EditText editText,TextInputLayout textInputLayout, String errorEmpty) throws Exception{
        textInputLayout.setError(null);
        String rawEditTextValue = editText.getText().toString();
        if (TextUtils.isEmpty(rawEditTextValue)) {
            editText.requestFocus();
            textInputLayout.setError(errorEmpty);
            throw new Exception(errorEmpty);
        }
    }
    public static void controlTextInput(EditText editText, TextInputLayout textInputLayout, String errorEmpty, Boolean isLengthChecked , Long lengthInputMin, String errorLength) throws Exception{
        textInputLayout.setError(null);
        controlTextInput(editText,textInputLayout,errorEmpty);
        String rawEditTextValue = editText.getText().toString();
        if (rawEditTextValue.length() < lengthInputMin) {
            editText.requestFocus();
            textInputLayout.setError(errorLength);
            throw new Exception(errorLength);
        }
    }
    public static void control2Passwords(EditText password1 ,EditText password2 , TextInputLayout layoutPass1, TextInputLayout layoutPass2, String errorPasswordMatch) throws Exception{
        layoutPass1.setError(null);
        String password1Str1 = password1.getText().toString();
        String password1Str2 = password2.getText().toString();
        if (!password1Str1.equals(password1Str2)) {
            password1.requestFocus();
            layoutPass1.setError(errorPasswordMatch);
            layoutPass2.setError(errorPasswordMatch);
            throw new Exception(errorPasswordMatch);
        }
    }
}
