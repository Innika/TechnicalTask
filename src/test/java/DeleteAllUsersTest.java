import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteAllUsersTest extends BaseTest {

    @Test
    @DisplayName("Delete all users via API")
    public void deleteAllUsersTest() throws Exception {
        newUserPage.createRandomUsers(3, 12);
        newUserPage.goToAllUsers();
        deleteAllUsers();

        verifyUsersDeleted();
    }

    @Step("Delete all users via API")
    private void deleteAllUsers() throws Exception {
        userService.deleteAllUsers();
    }

    @Step("Refresh page 'All users' page and verify if there are no users")
    private void verifyUsersDeleted() throws Exception {
        newUserPage.driver.navigate().refresh();
        assertNull(allUsersPage.getAllUsers());
    }
}
