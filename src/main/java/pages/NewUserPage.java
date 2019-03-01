package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewUserPage extends BasePage {
    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage enterName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public NewUserPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public NewUserPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public NewUserPage enterConfirmationPassword(String confirmationPassword) {
        confirmationPasswordInput.sendKeys(confirmationPassword);
        return this;
    }

    public NewUserPage submitForm(){
        submitButton.click();
        return this;
    }

    @FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "confirmationPassword")
    WebElement confirmationPasswordInput;

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;
}
