package ro.LearnByPLaying.Utilitare;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

import ro.LearnByPLaying.Beans.User;

/**
 * Created by Stefan on 5/4/2018.
 */

public class FirebaseRealtimeDBUtils {

    public static Boolean savingUSER(User user){
        Log.d("Activitati","<<<< IN FirebaseRealtimeDBUtils.savingUSER >>>>");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS/"+user.getUserFirebaseID());
        ref.setValue(user);
        Log.d("Activitati","<<<< OUT FirebaseRealtimeDBUtils.savingUSER >>>>");
        return true;

    }
    public static Boolean updateUSER(User user, HashMap objUPDATE){
        Log.d("Activitati","<<<< IN FirebaseRealtimeDBUtils.updateUSER >>>>");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d("Activitati","USERS/"+user.getUserFirebaseID());
        DatabaseReference ref = database.getReference("USERS/"+user.getUserFirebaseID());
        ref.updateChildren(objUPDATE);
        Log.d("Activitati","<<<< OUT FirebaseRealtimeDBUtils.updateUSER >>>>");
        return true;
    }
    public static Boolean isUserRegistered(User user){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS/"+user.getUserFirebaseID());
        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Activitati",getClass().getName()+" -------------yo-------");
                User user = dataSnapshot.getValue(User.class);
                Log.d("Activitati", "user ----> "+ user.getUserFirebaseID());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Activitati",getClass().getName()+" The read failed: " + databaseError.getCode());
            }
        });
        return true;
    }

    public static User getUser(String userFirebaseID){
        Log.d("Activitati","<<<< IN FirebaseRealtimeDBUtils.getUser() >>>> ");
        Log.d("Activitati","userFirebaseID: "+userFirebaseID);
        final User[] user = new User[1];
        //TODO: trebuie sa incerc sa creez metoda asta **getUser** aparent Firebase realtimeDB lor nu este wow, metodele lor de selectare din DB sunt sincrone si functile mele o iau inainte de a primi rezultatul din DB
        Log.d("Activitati","<<<< OUT FirebaseRealtimeDBUtils.getUser() >>>> ");
        return user[0];
    }

    public static void doSomethingWithUser(User user){
        Log.d("Activitati"," User email address from DB: "+ user.getEmailAddress());
    }

    public static String getUserFirebaseID(FirebaseAuth auth) {
        return auth.getUid();
    }

}
