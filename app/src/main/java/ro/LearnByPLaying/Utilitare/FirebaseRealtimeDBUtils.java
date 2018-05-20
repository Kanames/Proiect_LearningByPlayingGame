package ro.LearnByPLaying.Utilitare;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;

import ro.LearnByPLaying.Beans.Courses;
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


    public static String getUserFirebaseID(FirebaseAuth auth) {
        return auth.getUid();
    }

    public static ArrayList<Courses> getCoursesList(){
        ArrayList<Courses> courses = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("COURSES");

        // Create a storage reference from our app
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");
                Courses course = dataSnapshot.getValue(Courses.class);
                Log.d("Activitati", "course name: ----> "+ course.getCourseName());
                Log.d("Activitati", "course chapter: ----> "+ course.getCourseChapter());
                Log.d("Activitati", "course urlBackground: ----> "+ course.getLinkBackground());
                if(course.getLinkBackground() != null ){
                    SystemUtils.saveTempPNG(firebaseStorage.getReferenceFromUrl(course.getLinkBackground()),course);
                }
                courses.add(course);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return courses;
    }


}
