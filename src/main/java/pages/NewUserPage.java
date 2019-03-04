package pages;

import io.qameta.allure.Step;
import models.User;
import models.UserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static models.User.CorrectRandomData.*;

public class NewUserPage extends BasePage {
    public Error error;

    public NewUserPage(WebDriver driver, String url, UserService userService) {
        super(driver, url, userService);
        error = new Error(driver);
    }

    @Step("Navigate to 'New User' page")
    public NewUserPage navigateTo() {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        takeScreenshot();
        return this;
    }

    private void enterName(String name) {
        nameInput.sendKeys(name);
    }

    private void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    private void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    private void enterConfirmationPassword(String confirmationPassword) {
        confirmationPasswordInput.sendKeys(confirmationPassword);
    }

    @Step("Submit form")
    private void submitForm() {
        takeScreenshot();
        submitButton.click();
        takeScreenshot();
    }

    @Step("Go to 'All users' page")
    public void goToAllUsers() {
        allUsersButton.click();
        takeScreenshot();
    }

    @Step("Create random count of random users via API")
    public List<User> createRandomUsers(int minUsersCount, int maxUsersCount) throws Exception {
        int count = new Random().nextInt(maxUsersCount - minUsersCount) + minUsersCount;
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            users.add(postRandomUser());
        }
        return users;
    }

    @Step("Create a random user via UI")
    public User createRandomUser() {
        var user = new User(getRandomName(), getRandomEmail(), getRandomPassword());
        fulfilForm(user);
        return user;
    }

    public User postRandomUser() throws Exception {
        var user = new User(getRandomName(), getRandomEmail(), getRandomPassword());
        userService.postUser(user);
        return user;
    }

    @Step("Fulfil form")
    public NewUserPage fulfilForm(User user) {
        user.getUserCredentials();

        enterName(user.name);
        enterEmail(user.email);
        enterPassword(user.password);
        enterConfirmationPassword(user.confirmationPassword);

        submitForm();
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

    @FindBy(linkText = "All User")
    WebElement allUsersButton;

    public class Error extends BasePage {
        public Error(WebDriver driver) {
            super(driver);
        }

        @FindBy(id = "user.name.error")
        public WebElement nameError;

        @FindBy(id = "user.email.error")
        public WebElement emailError;

        @FindBy(id = "user.password.error")
        public WebElement passwordError;

        @FindBy(id = "user.confirmationPassword.error")
        public WebElement confirmationPasswordError;
    }
}
