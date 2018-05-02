package ro.LearnByPLaying.FlowActivitati;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.example.stefan.proiect_learningbyplayinggame.R;

import javax.security.auth.login.LoginException;
import ro.LearnByPLaying.Activitati.LoginActivity;
/**
 * Created by Stefan on 4/18/2018.
 * Clasa ce va contine logica de flow in activitatea de Login.
 */
public class FlowLogin{

    public void execute(EditText email, EditText parolaUser) throws LoginException{
        Log.d("Activitati", "<<< Iesit in FlowLogin.execute() >>>");
        if(TextUtils.isEmpty(email.getText().toString())){
            email.requestFocus();
            email.setError("Please enter your e-mail");
            throw new LoginException("Please enter the e-mail");
        }
        if(TextUtils.isEmpty(parolaUser.getText().toString())){
            parolaUser.requestFocus();
            parolaUser.setError("Please enter the password");
            throw new LoginException("Completeaza campul Password");

        }
        String emailStr = email.getText().toString();
        String passStr  = parolaUser.getText().toString();
        Log.d("Activitati", "numeUser: "+emailStr+" parolaUser: "+passStr);
        Log.d("Activitati", "<<< Iesit in FlowLogin.execute() >>>");
    }

}
