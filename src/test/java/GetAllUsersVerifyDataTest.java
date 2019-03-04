import io.qameta.allure.Step;
import jdk.jfr.Description;
import models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import verifiers.Verifier;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsersVerifyDataTest extends BaseTest {

    List<User> expectedUsers = null;
    List<User> createdUsersApi = null;
    List<User> createdUsersUi = null;

    @Test
    @DisplayName("Get all users and verify their data")
    @Description("Create users, verify the data created. IDs, emails and names have to be unique." +
            " Password has to be the same as the repeated one")
    public void getAllUsersVerifyDataTest() throws Exception {
        expectedUsers = newUserPage.createRandomUsers(2, 15);

        var nameComparator = Comparator.comparing(User::getName);
        expectedUsers.sort(nameComparator);

        createdUsersApi = userService.getAllUsers();
        createdUsersApi.sort(nameComparator);

        newUserPage.goToAllUsers();
        createdUsersUi = allUsersPage.getAllUsers();
        createdUsersUi.sort(nameComparator);

        verifyIfUsersCreatedAreTheSameAsProvided();

        verifyIfNameEmailIdAreUnique();
    }s

    @Step("Verify if users created are the same as provided")
    private void verifyIfUsersCreatedAreTheSameAsProvided() {
        Verifier.verifyIfUsersCreatedAreTheSameAsProvided(
                expectedUsers, createdUsersApi, createdUsersUi);
    }

    @Step("Verify if names, emails and IDs(for users gotten via API) are unique")
    private void verifyIfNameEmailIdAreUnique() {
        int usersCount = createdUsersApi.size();
        Assertions.assertThat(usersCount).isEqualTo(
                createdUsersApi.stream().map(u -> u.name).collect(Collectors.toSet()).size());

        Assertions.assertThat(usersCount).isEqualTo(
                createdUsersApi.stream().map(u -> u.email).collect(Collectors.toSet()).size());

        Assertions.assertThat(usersCount).isEqualTo(
                createdUsersApi.stream().map(u -> u.id).collect(Collectors.toSet()).size());
    }
}
