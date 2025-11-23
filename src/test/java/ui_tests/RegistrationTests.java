package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.UserFactory.*;

@Listeners(TestNGListener.class)
public class RegistrationTests extends ApplicationManager {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void goToRegPage(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = {"smoke", "user"})
    public void registrationPositiveTest(){
        User user = positiveUser();
        loginPage.typeRegForm(user);
        Assert.assertTrue(new ContactsPage(getDriver())
                .isTextNoContactsPresent("No Contacts here!"));
    }

    @Test(groups = "negative")
    public void registrationNegativeTest_wrongEmail(){
        User user = positiveUser();
        user.setUsername("wrong email");
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Wrong email or password format"));
    }
}