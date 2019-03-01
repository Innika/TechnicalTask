package pages;

import controllers.AllUsersTableController;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllUsersPage extends BasePage {
    public AllUsersPage(WebDriver driver){
        super(driver);
    }

    public List<User> getAllUsers() throws Exception{
        return new AllUsersTableController(usersTable).getUsers();
    }

    @FindBy(id = "users")
    WebElement usersTable;
}
