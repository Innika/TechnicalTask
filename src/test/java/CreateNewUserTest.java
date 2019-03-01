import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static models.User.CorrectRandomData.*;

public class CreateNewUserTest extends BaseTest {
    @Test
    @DisplayName("Create user with unique name and email")
    public void createUserWithCorrectValues() throws Throwable {
        //TODO randomize UNIQUE parameters
        //TODO handle email format
        //TODO implement allure
        var user = new User(getRandomName(), getRandomEmail(), getRandomPassword());
        newUserPage
                .enterName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .enterConfirmationPassword(user.confirmationPassword)
                .submitForm();

        var users = allUsersPage.getAllUsers();
        //TODO verify results using assertj to compare the object gotten via api and the submitted one
    }
}
