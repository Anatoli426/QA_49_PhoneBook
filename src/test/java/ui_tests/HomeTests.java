package ui_tests;

import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TakeScreenShot;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class HomeTests extends ApplicationManager {

    @Test
    public void firstTest(){
        System.out.println("first test");
        HomePage homePage = new HomePage(getDriver());
        //TakeScreenShot.takeScreenShot((TakesScreenshot)getDriver());
    }

    //  setup (BeforeMethod)  --> firstTest (Test) --> tearDown (AfterMethod)
}