package ro.LearnByPLaying.Beans;

import android.view.View;

/**
 * Created by Stefan on 5/18/2018.
 */

public class SettingProfileView {
    private static String nameSetting;
    private static String hintEditTextSetting;
    private static String descriptionSetting;
    private static String valueSetting;
    private static View.OnClickListener actionUpdateValue;


    public static String getNameSetting() {
        return nameSetting;
    }

    public static void setNameSetting(String nameSetting) {
        SettingProfileView.nameSetting = nameSetting;
    }

    public static String getHintEditTextSetting() {
        return hintEditTextSetting;
    }

    public static void setHintEditTextSetting(String hintEditTextSetting) {
        SettingProfileView.hintEditTextSetting = hintEditTextSetting;
    }

    public static String getDescriptionSetting() {
        return descriptionSetting;
    }

    public static void setDescriptionSetting(String descriptionSetting) {
        SettingProfileView.descriptionSetting = descriptionSetting;
    }

    public static String getValueSetting() {
        return valueSetting;
    }

    public static void setValueSetting(String valueSetting) {
        SettingProfileView.valueSetting = valueSetting;
    }

    public static View.OnClickListener getActionUpdateValue() {
        return actionUpdateValue;
    }

    public static void setActionUpdateValue(View.OnClickListener actionUpdateValue) {
        SettingProfileView.actionUpdateValue = actionUpdateValue;
    }
}
