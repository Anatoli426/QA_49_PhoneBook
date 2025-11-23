package pages;

import dto.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddPage extends BasePage {

    public AddPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[1]")
    WebElement inputName;
    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[2]")
    WebElement inputLastName;
    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[3]")
    WebElement inputPhone;
    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[4]")
    WebElement inputEmail;
    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[5]")
    WebElement inputAddress;
    @FindBy(xpath = "//div[@class='add_form__2rsm2']/input[last()]")
    WebElement inputDescription;
    @FindBy(xpath = "//b/..")
    WebElement btnSave;

    public void typeContactForm(Contact contact) {
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.sendKeys(contact.getAddress());
        inputDescription.sendKeys(contact.getDescription());
        btnSave.click();
    }


}