package ro.LearnByPLaying.Utilitare;

import android.os.Bundle;
import android.util.Log;

import ro.LearnByPLaying.Beans.User;

/**
 * Created by Stefan on 5/14/2018.
 */

public class FunctionalMethods{

    public static Object getObjectSession(Bundle extras,String wantYouWANT,String ACTION) {
        ISession action = null;
        if(ACTION.equals("GET")){
            action = methodGetObjectSession;
            return action.getObjectFromSession(extras,wantYouWANT);
        }
        return null;
    }

    private static ISession methodGetObjectSession = (extras , wantYouWANT) -> {
        if (extras != null) {
            return (User) extras.getSerializable(wantYouWANT);
        }else{
            //throw new Exception("Error: no Bundle of extra found !");
        }
        return null;
    };

}

@FunctionalInterface
interface ISession {
    Object getObjectFromSession(Bundle extras,String wantYouWANT);
}


