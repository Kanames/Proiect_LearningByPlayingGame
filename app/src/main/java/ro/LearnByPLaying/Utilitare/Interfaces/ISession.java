package ro.LearnByPLaying.Utilitare.Interfaces;

import android.os.Bundle;

/**
 * Created by Stefan on 5/15/2018.
 */

@FunctionalInterface
public interface ISession {
    Object getObjectFromSession(Bundle extras, String wantYouWANT);
}
