package ro.LearnByPLaying.FlowActivitati;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

import ro.LearnByPLaying.Activitati.MainActivity;

/**
 * Created by Stefan on 4/18/2018.
 * Clasa ce va contine logica de flow in activitatea de Login.
 */
public class FlowLogin {
    public void execute(EditText email, EditText parolaUser) throws LoginException{
        Log.d("Login", "<<< Iesit in FlowLogin.execute() >>>");
        if("".equals(email.getText().toString())){
            throw new LoginException("Completeaza campul E-mail");
        }
        if("".equals(parolaUser.getText().toString())){
            throw new LoginException("Completeaza campul Password");
        }
        String emailStr = email.getText().toString();
        String passStr  = parolaUser.getText().toString();
        Log.d("Login", "numeUser: "+emailStr+" parolaUser: "+passStr);
        Log.d("Login", "<<< Iesit in FlowLogin.execute() >>>");
    }

}
