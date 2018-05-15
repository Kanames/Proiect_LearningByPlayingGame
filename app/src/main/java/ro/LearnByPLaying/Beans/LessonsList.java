package ro.LearnByPLaying.Beans;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Stefan on 5/14/2018.
 */

public class LessonsList {

    java.util.ArrayList<String> LESSONS_TITLES;
    ArrayList<Integer> LESSONS_IMGS;
    ArrayList<View.OnClickListener> LESSONS_IMG_ACTIONS;
    ArrayList<Integer> LESSONS_BACKGROUND_COLORS;

    public LessonsList(ArrayList<String> LESSONS_TITLES, ArrayList<Integer> LESSONS_IMGS, ArrayList<View.OnClickListener> LESSONS_IMG_ACTIONS, ArrayList<Integer> LESSONS_BACKGROUND_COLORS) {
        this.LESSONS_TITLES = LESSONS_TITLES;
        this.LESSONS_IMGS = LESSONS_IMGS;
        this.LESSONS_IMG_ACTIONS = LESSONS_IMG_ACTIONS;
        this.LESSONS_BACKGROUND_COLORS = LESSONS_BACKGROUND_COLORS;
    }

    public ArrayList<String> getLESSONS_TITLES() {
        return LESSONS_TITLES;
    }

    public void setLESSONS_TITLES(ArrayList<String> LESSONS_TITLES) {
        this.LESSONS_TITLES = LESSONS_TITLES;
    }

    public ArrayList<Integer> getLESSONS_IMGS() {
        return LESSONS_IMGS;
    }

    public void setLESSONS_IMGS(ArrayList<Integer> LESSONS_IMGS) {
        this.LESSONS_IMGS = LESSONS_IMGS;
    }

    public ArrayList<View.OnClickListener> getLESSONS_IMG_ACTIONS() {
        return LESSONS_IMG_ACTIONS;
    }

    public void setLESSONS_IMG_ACTIONS(ArrayList<View.OnClickListener> LESSONS_IMG_ACTIONS) {
        this.LESSONS_IMG_ACTIONS = LESSONS_IMG_ACTIONS;
    }

    public ArrayList<Integer> getLESSONS_BACKGROUND_COLORS() {
        return LESSONS_BACKGROUND_COLORS;
    }

    public void setLESSONS_BACKGROUND_COLORS(ArrayList<Integer> LESSONS_BACKGROUND_COLORS) {
        this.LESSONS_BACKGROUND_COLORS = LESSONS_BACKGROUND_COLORS;
    }
}
