import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteAllUsersTest extends BaseTest {

    @Test
    @DisplayName("Delete all users via API")
    public void deleteAllUsersTest() throws Exception {
        newUserPage.createRandomUsers(3, 12);
        newUserPage.goToAllUsers();

        userService.deleteAllUsers();

        newUserPage.driver.navigate().refresh();
        assertNull(allUsersPage.getAllUsers());
    }
}
