import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("Create user with unique name and email")
    public void createUserWithCorrectValuesUiTest() throws Throwable {
        var expectedUser = newUserPage.createRandomUser();

        var createdUsers = allUsersPage.getAllUsers();
        assertTrue(createdUsers.stream().anyMatch(
                u -> u.email.equals(expectedUser.email)
                        && u.name.equals(expectedUser.name)));
    }
}