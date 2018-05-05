package ro.LearnByPlaying.UtilitareTest.StringUtilsTests;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


import ro.LearnByPLaying.Utilitare.StringUtils;

/**
 * Created by Stefan on 4/22/2018.
 */

public class StringUtilsTestCollections {
    private static List<String> listSimpleScenario;
    private static List<Object> listAdvancedScenario;


    public static void initializareScenari(){
        listSimpleScenario = new ArrayList<>();
        listSimpleScenario.add("TestSimple1");
        listSimpleScenario.add("TestSimple2");
        listSimpleScenario.add("TestSimple3");

        listAdvancedScenario = new ArrayList<>();
        List<String> listAdvancedScenario1 = new ArrayList<>();
        List<Integer> listAdvancedScenario2 = new ArrayList<>();
        listAdvancedScenario1.add("TestAdvanced1");
        listAdvancedScenario1.add("TestAdvanced2");
        listAdvancedScenario2.add(1);
        listAdvancedScenario2.add(2);
        listAdvancedScenario.add(listAdvancedScenario1);
        listAdvancedScenario.add(listAdvancedScenario2);
    }

    @Test
    public void humanReadableObjects() {
        initializareScenari();
        System.out.println(StringUtils.trfOut(listSimpleScenario));
        System.out.println(StringUtils.trfOut(listAdvancedScenario));
    }

    @Test
    public void humanReadableObjects_betterMethod() {
        initializareScenari();
        System.out.println(StringUtils.readObject(listSimpleScenario));
        System.out.println(StringUtils.readObject(listAdvancedScenario));
    }
}
