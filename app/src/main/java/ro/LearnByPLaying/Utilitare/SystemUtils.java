package ro.LearnByPLaying.Utilitare;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import ro.LearnByPLaying.Beans.Courses;

/**
 * Created by Stefan on 5/18/2018.
 */

public class SystemUtils {
    public static String saveTempPNG(StorageReference getReferenceRAW,Courses course){
        StorageReference getReference = getReferenceRAW;
        File localFile = null;
        try {
            localFile = File.createTempFile("images", "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File finalLocalFile = localFile;
        getReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.d("Activitati", "file reference: - "+ finalLocalFile.toString());
                course.setTempLinkBackgroundImg(finalLocalFile.toString());
                // Local temp file has been created
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("Activitati", "file exception getFile: - "+ exception.getMessage());
                // Handle any errors
            }
        });
        return finalLocalFile.toString();
    }
}
