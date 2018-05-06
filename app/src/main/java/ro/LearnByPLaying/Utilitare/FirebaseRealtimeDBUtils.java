package ro.LearnByPLaying.Utilitare;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS/"+user.getUserFirebaseID());
        ref.setValue(user);
        return true;

    }
    public static Boolean updateUSER(User user,HashMap<String,Object> objUPDATE){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS/"+user.getUserFirebaseID());
        objUPDATE.put("nickname", "Amazing Grace");
        ref.updateChildren(objUPDATE);

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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.child(userFirebaseID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                      Log.d("Activitati","child: "+child.getValue());
//                    user[0] = dataSnapshot.getValue(User.class);
//                    doSomethingWithUser(user[0]);
//                    Log.d("Activitati","User object: "+ user[0]);
//                    Log.d("Activitati","User email address from DB: "+ user[0].getEmailAddress());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Activitati",getClass().getName()+" DB ERROR: "+databaseError.getMessage());
            }

        });
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
