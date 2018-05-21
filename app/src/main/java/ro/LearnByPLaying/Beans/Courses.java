package ro.LearnByPLaying.Beans;

/**
 * Created by Stefan on 5/18/2018.
 */

public class Courses {
    private String courseName;
    private String courseChapter;
    private String linkBackground;
    private String tempLinkBackgroundImg;

    public String getTempLinkBackgroundImg() {
        return tempLinkBackgroundImg;
    }

    public void setTempLinkBackgroundImg(String tempLinkBackgroundImg) {
        this.tempLinkBackgroundImg = tempLinkBackgroundImg;
    }

    public String getLinkBackground() {
        return linkBackground;
    }

    public void setLinkBackground(String linkBackground) {
        this.linkBackground = linkBackground;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseChapter() {
        return courseChapter;
    }

    public void setCourseChapter(String courseChapter) {
        this.courseChapter = courseChapter;
    }
}
