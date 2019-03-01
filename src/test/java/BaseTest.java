import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AllUsersPage;
import pages.NewUserPage;

public class BaseTest {
    WebDriver driver;
    NewUserPage newUserPage;
    AllUsersPage allUsersPage;

    @BeforeEach
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://85.93.17.135:9000");

        newUserPage = new NewUserPage(driver);
        allUsersPage = new AllUsersPage(driver);

        //TODO: Delete users
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        //TODO: Delete users
    }
}
