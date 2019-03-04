import models.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AllUsersPage;
import pages.NewUserPage;

public class BaseTest {
    public final String url = "http://85.93.17.135:9000";
    WebDriver driver;
    UserService userService;
    NewUserPage newUserPage;
    AllUsersPage allUsersPage;

    @BeforeEach
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        userService = new UserService(url);
        newUserPage = new NewUserPage(driver, url, userService);
        allUsersPage = new AllUsersPage(driver);

        userService.deleteAllUsers();
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        userService.deleteAllUsers();
    }
}