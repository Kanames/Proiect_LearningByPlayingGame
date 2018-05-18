package ro.LearnByPLaying.Utilitare;

import android.os.Bundle;

import ro.LearnByPLaying.Beans.User;
import ro.LearnByPLaying.Utilitare.Interfaces.ISession;

/**
 * Created by Stefan on 5/14/2018.
 */

public class FunctionalMethods{

    private static ISession varMethodGetUser = (extras , wantYouWANT) -> {
        if (extras != null) {
            return (User) extras.getSerializable(wantYouWANT);
        }else{
            //throw new Exception("Error: no Bundle of extra found !");
        }
        return null;
    };

    public static Object methodGetUser(Bundle extras, String wantYouWANT, String ACTION) {
        ISession action = null;
        if(ACTION.equals("GET")){
            action = varMethodGetUser;
            return action.getObjectFromSession(extras,wantYouWANT);
        }
        return null;
    }



}



