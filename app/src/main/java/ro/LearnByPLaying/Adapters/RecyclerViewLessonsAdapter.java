package ro.LearnByPLaying.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.stefan.proiect_learningbyplayinggame.R;
import java.util.ArrayList;

import ro.LearnByPLaying.Beans.LessonsList;


/**
 * Created by Stefan on 5/9/2018.
 */
public class RecyclerViewLessonsAdapter extends RecyclerView.Adapter<RecyclerViewLessonsAdapter.ViewHolderLesson>{
    private static final String TAG = "RecyclerViewLessonsAdapter";
    private ArrayList<String> LESSONS_TITLES = new ArrayList<>();
    private ArrayList<Integer> LESSONS_IMGS = new ArrayList<>();
    private ArrayList<View.OnClickListener> LESSONS_IMG_ACTIONS = new ArrayList<>();
    private ArrayList<Integer> LESSONS_BACKGROUND_COLORS = new ArrayList<>();
    private Context nContext;

    //Crearea constructorului pentru Adapter
    //View-ul va trebui sa poata afla: ce titlu are, ce img/logo are, ce culoare de background sa aiba, ce actiune onClick sa faca.
    public RecyclerViewLessonsAdapter(LessonsList lessonsList, Context nContext) {
        Log.d("Activitati", "<<<<< IN RecyclerViewAdapter() >>>>");
        this.LESSONS_TITLES =           lessonsList.getLESSONS_TITLES();
        this.LESSONS_IMGS =             lessonsList.getLESSONS_IMGS();
        this.LESSONS_IMG_ACTIONS =      lessonsList.getLESSONS_IMG_ACTIONS();
        this.LESSONS_BACKGROUND_COLORS= lessonsList.getLESSONS_BACKGROUND_COLORS();
        this.nContext = nContext;
    }
    @Override
    public RecyclerViewLessonsAdapter.ViewHolderLesson onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Activitati", "RecyclerViewAdapter - RecyclerViewAdapter.onCreateViewHolder()");
        Log.d("Activitati", "ViewGroup: " + parent + " viewType: " + viewType);
        return new RecyclerViewLessonsAdapter.ViewHolderLesson(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lesson_individual, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolderLesson holder, int position) {
        Log.d("Activitati", "onBindViewHolder: CALLED");
        holder.main_imgViewIconLesson.setImageResource(LESSONS_IMGS.get(position));
        holder.main_textViewLessonsTitle.setText(LESSONS_TITLES.get(position));
        holder.main_imgViewIconLesson.setOnClickListener(LESSONS_IMG_ACTIONS.get(position));
        holder.main_linearLayoutLesson.setBackgroundColor(LESSONS_BACKGROUND_COLORS.get(position));
    }
    @Override
    public int getItemCount() {
        return LESSONS_TITLES.size();
    }
    /**
     * Tine widget-urile in memorie pentru fiecare widget ...
     */
    public class ViewHolderLesson extends RecyclerView.ViewHolder{
        ImageView main_imgViewIconLesson;
        TextView main_textViewLessonsTitle;
        LinearLayout main_linearLayoutLesson;
        public ViewHolderLesson(View item){
            super(item);
            main_imgViewIconLesson = item.findViewById(R.id.main_imgViewIconLesson);
            main_textViewLessonsTitle = item.findViewById(R.id.main_textViewLessonsTitle);
            main_linearLayoutLesson = item.findViewById(R.id.main_linearLayoutLesson);
        }
    }


}
