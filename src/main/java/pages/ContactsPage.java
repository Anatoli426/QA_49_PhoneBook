package pages;

import dto.Contact;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContactsHeader;
    @FindBy(xpath = "//div[@class='contact-page_message__2qafk']")
    WebElement divTextNoContacts;
    @FindBy(className = "contact-item_card__2SOIM")
    List<WebElement> contactsList;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div/div[last()]/h2")
    WebElement lastElementList;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div")
    WebElement divElementsList;

    @FindBy(xpath = "//div[contains(@class,'contact-item-detailed_card')]")
    WebElement itemDetailedCard;

    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemove;
    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@placeholder='desc']")
    WebElement inputDesc;

    public boolean isTextContactsPresent(String text) {
        return isTextInElementPresent(btnContactsHeader, text);
    }

    public boolean isTextNoContactsPresent(String text) {
        System.out.println(divTextNoContacts.getText());
        return isTextInElementPresent(divTextNoContacts, text);
    }

    public int getNumberOfContacts() {
//        for (WebElement element: contactsList){
//            System.out.println(element.getText());
//        }
        return contactsList.size();
    }

    public boolean isContactPresent(Contact contact) {
        for (WebElement element : contactsList) {
            if (element.getText().contains(contact.getName())
                    && element.getText().contains(contact.getPhone())) {
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }


    public void clickLastContact() {
        lastElementList.click();
    }


    public void scrollToLastElementList() {
        Actions actions = new Actions(driver);
        //actions.scrollToElement(lastElementList).perform();  example 1
//        int deltaY = driver.findElement(
//                By.xpath("//div[@class='contact-page_leftdiv__yhyke']/div"))
//                .getSize().getHeight();
        int deltaY = divElementsList.getSize().getHeight();
        System.out.println("-->" + deltaY);
        WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin
                .fromElement(contactsList.get(0));
        pause(3);
        actions.scrollFromOrigin(scrollOrigin, 0, deltaY).perform();
    }

    public void scrollToLastElementListJS() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getContactCardTest() {
        return itemDetailedCard.getText();
    }

    public void deleteFirstContact() {
        contactsList.get(0).click();
        btnRemove.click();
    }

    public void typeEditForm(Contact contact) {
        contactsList.get(0).click();
        btnEdit.click();
        inputName.clear();
        inputName.sendKeys(contact.getName());
        inputLastName.clear();
        inputLastName.sendKeys(contact.getLastName());
        inputPhone.clear();
        inputPhone.sendKeys(contact.getPhone());
        inputEmail.clear();
        inputEmail.sendKeys(contact.getEmail());
        inputAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputAddress.sendKeys(contact.getAddress());
//        inputDesc.clear();
//        inputDesc.sendKeys(contact.getDescription());
        btnSave.click();
    }
}