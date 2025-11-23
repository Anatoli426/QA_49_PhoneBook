package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        setDriver(driver);
        //driver.get("https://telranedu.web.app/home");
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/login']")
    WebElement btnLoginHeader;

    //WebElement btnLoginHeader = driver.findElement(By.cssSelector("a[href='/login']"));
    public void clickBtnLoginHeader(){
        btnLoginHeader.click();
    }
}