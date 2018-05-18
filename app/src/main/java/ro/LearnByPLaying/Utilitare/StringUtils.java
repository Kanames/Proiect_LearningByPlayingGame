package ro.LearnByPLaying.Utilitare;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.example.stefan.proiect_learningbyplayinggame.R;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.security.auth.login.LoginException;

import ro.LearnByPLaying.Utilitare.Interfaces.IFunction1;
import ro.LearnByPLaying.Utilitare.Interfaces.IFunction2;
import ro.LearnByPLaying.Utilitare.Interfaces.IFunction3;

/**
 * Created by Stefan on 4/18/2018.
 */

public class StringUtils {

    private static IFunction1 func_TrfOut = (obj) -> {
        StringBuffer representation = new StringBuffer();
        representation.append(obj.getClass().getName());
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object value;
                value = field.get(obj);
                representation.append(" [" + name + ":" + value + "] ");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return representation.toString();
    };
    private static IFunction1 func_ReadObject = (obj) -> {
        if(obj == null){
            return null;
        }
        if(obj instanceof String){
            return (String) obj;
        }
        if(obj instanceof Date){
            return obj ==null ? null : obj.toString();
        }
        if(obj instanceof Number){
            return obj ==null ? null : obj.toString();
        }
        if(obj instanceof Map){
            final StringBuffer representation = new StringBuffer("[");
            Map map = (Map) obj;
            if(!map.isEmpty()){
                for(final Object item: map.keySet()){
                    representation.append(item).append("=").append(readObject(map.get(item))).append(", ");
                }
            }
            representation.append("]");
            return obj ==null ? null : obj.toString();
        }
        if(obj instanceof EditText){
            return obj.toString();
        }
        if(!(obj instanceof Collection)){
            return trfOut(obj);
        }
        final StringBuffer representation = new StringBuffer("[");
        for(final Object item : (Collection) obj){
            representation.append(readObject(item)).append(", ");
        }
        representation.append("]");
        return representation.toString();
    };
    private static IFunction2 func_controlTextInputLayout = (obj, errorEmpty) -> {
        EditText edTxt = (EditText) obj;
        String rawEditTextValue = edTxt.getText().toString();
        if (TextUtils.isEmpty(rawEditTextValue)) {
            edTxt.requestFocus();
            edTxt.setError((CharSequence) errorEmpty);
            //throw new Exception((String) errorEmpty);
        }
        return null;
    };
    private static IFunction3 func_controlTextInputLayout2 = (editTxt, txtInputLayout, errEmpty) -> {
        String errorEmpty = (String) errEmpty;
        EditText editText = (EditText) editTxt;
        TextInputLayout textInputLayout = (TextInputLayout) txtInputLayout;
        textInputLayout.setError(null);
        String rawEditTextValue = editText.getText().toString();
        if (TextUtils.isEmpty(rawEditTextValue)) {
            editText.requestFocus();
            textInputLayout.setError(errorEmpty);
            //throw new Exception(errorEmpty);
        }
        return null;
    };

    public static String trfOut(Object object){
        return (String) func_TrfOut.apply(object);
    }

    public static String readObject(Object object){
        return (String) func_ReadObject.apply(object);
    }

    public static void controlTextInput(EditText editText, String errorEmpty) throws Exception{
         func_controlTextInputLayout.apply(editText,errorEmpty);
    }

    public static void controlTextInput(EditText editText,TextInputLayout textInputLayout, String errorEmpty) throws Exception{
        func_controlTextInputLayout2.apply(editText,textInputLayout,errorEmpty);
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


    /**
     * Old method in OOP style
     * @param object
     * @return
     */
    public static String trfOut_oldOOP(Object object){
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
        return representation.toString();
    }
    public static void controlTextInput_oldOOP(EditText editText,TextInputLayout textInputLayout, String errorEmpty) throws Exception{
        textInputLayout.setError(null);
        String rawEditTextValue = editText.getText().toString();
        if (TextUtils.isEmpty(rawEditTextValue)) {
            editText.requestFocus();
            textInputLayout.setError(errorEmpty);
            throw new Exception(errorEmpty);
        }
    }
    public static void controlTextInput_oldOOP(EditText editText, TextInputLayout textInputLayout, String errorEmpty, Boolean isLengthChecked , Long lengthInputMin, String errorLength) throws Exception{
        textInputLayout.setError(null);
        controlTextInput(editText,textInputLayout,errorEmpty);
        String rawEditTextValue = editText.getText().toString();
        if (rawEditTextValue.length() < lengthInputMin) {
            editText.requestFocus();
            textInputLayout.setError(errorLength);
            throw new Exception(errorLength);
        }
    }
    public static void control2Passwords_oldOOP(EditText password1 ,EditText password2 , TextInputLayout layoutPass1, TextInputLayout layoutPass2, String errorPasswordMatch) throws Exception{
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
