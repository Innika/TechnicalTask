package pages;

import io.qameta.allure.Attachment;
import models.UserService;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    private static final int TIMEOUT = 20;

    public WebDriver driver;
    public WebDriverWait wait;
    String url;
    UserService userService;

    BasePage(WebDriver driver, String url, UserService userService) {
        this.url = url;
        this.userService = userService;
        setUp(driver);
    }

    BasePage(WebDriver driver) {
        setUp(driver);
    }

    private void setUp(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
        PageFactory.initElements(this.driver, this);
    }

    @Attachment(type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
