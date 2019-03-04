package verifiers;

import models.User;
import org.assertj.core.api.Assertions;

import java.util.List;

public class Verifier {
    public static void verifyIfUsersCreatedAreTheSameAsProvided(List<User> usersProvided,
                                                                List<User> createdUsersApi,
                                                                List<User> createdUsersUi) {
        if (usersProvided == null && createdUsersApi == null
                && createdUsersUi == null) {
            return;
        }

        Assertions.assertThat(usersProvided.size())
                .isEqualTo(usersProvided.size())
                .isEqualTo(createdUsersUi.size());

        for (int i = 0; i < usersProvided.size(); i++) {
            Assertions.assertThat(usersProvided.get(i))
                    .isEqualToIgnoringGivenFields(createdUsersApi.get(i), "id", "confirmationPassword")
                    .isEqualToComparingFieldByFieldRecursively(createdUsersUi.get(i));
        }
    }
}
