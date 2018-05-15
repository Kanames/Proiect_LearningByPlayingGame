package ro.LearnByPLaying.Beans;

import java.io.Serializable;

/**
 * Created by Stefan on 5/9/2018.
 */

public class ConfigLessonPanel implements Serializable {
    private Integer wallpaper;
    private Integer colorTheme;
    private String wallText;
    private String titleLesson;

    public ConfigLessonPanel(Integer wallpaper, Integer colorTheme, String wallText, String titleLesson) {
        this.wallpaper = wallpaper;
        this.colorTheme = colorTheme;
        this.wallText = wallText;
    }


    public String getTitleLesson() {
        return titleLesson;
    }

    public void setTitleLesson(String titleLesson) {
        this.titleLesson = titleLesson;
    }

    public Integer getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(Integer wallpaper) {
        this.wallpaper = wallpaper;
    }

    public Integer getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(Integer colorTheme) {
        this.colorTheme = colorTheme;
    }

    public String getWallText() {
        return wallText;
    }

    public void setWallText(String wallText) {
        this.wallText = wallText;
    }
}
