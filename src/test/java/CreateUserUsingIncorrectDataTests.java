import io.qameta.allure.Step;
import jdk.jfr.Description;
import models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import verifiers.Verifier;

import java.util.ArrayList;
import java.util.List;

import static models.User.CorrectRandomData.*;

public class CreateUserUsingIncorrectDataTests extends BaseTest {
    @Test
    @DisplayName("Create user with NOT unique NAME and unique email")
    public void createUserWithNotUniqueNameTest() throws Throwable {
        var user1 = newUserPage.createRandomUser();

        createUserNotUniqueName(user1);

        verifyOnlyUniqueUserWasCreated(user1);
    }

    @Step("Create user with NOT unique NAME and unique email")
    private void createUserNotUniqueName(User uniqueUser) {
        var user2 = new User();
        user2.setName(uniqueUser.name)
                .setEmail(uniqueUser.email + "user2")
                .setPassword(uniqueUser.password + "user2")
                .setConfirmationPassword(user2.password);

        newUserPage.navigateTo()
                .fulfilForm(user2)
                .wait.until(
                ExpectedConditions.visibilityOf(newUserPage.error.nameError));
        Assertions.assertThat("Must be unique")
                .isEqualTo(newUserPage.error.nameError.getText());
    }

//    @Test
    @DisplayName("Create user with NOT unique EMAIL and unique name")
    public void createUserWithNotUniqueEmailTest() throws Throwable {
        var user1 = newUserPage.createRandomUser();
        createUserNotUniqueEmail(user1);

        verifyOnlyUniqueUserWasCreated(user1);
    }

    @Step("Create user with NOT unique EMAIL and unique name")
    private void createUserNotUniqueEmail(User uniqueUser) {
        var user2 = new User();
        user2.setName(uniqueUser.name + "user2")
                .setEmail(uniqueUser.email)
                .setPassword(uniqueUser.password + "user2")
                .setConfirmationPassword(user2.password);

        newUserPage.navigateTo()
                .fulfilForm(user2)
                .wait.until(
                ExpectedConditions.visibilityOf(newUserPage.error.emailError));

        Assertions.assertThat("Must be unique")
                .isEqualTo(newUserPage.error.emailError.getText());
    }

    @Step("Verify that only unique user was created")
    private void verifyOnlyUniqueUserWasCreated(User user) throws Exception {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);
        newUserPage.goToAllUsers();

        Verifier.verifyIfUsersCreatedAreTheSameAsProvided(
                expectedUsers, userService.getAllUsers(), allUsersPage.getAllUsers());
    }

//    @Test
    @DisplayName("Create a user with no data")
    @Description("Name, email and password are required for user creation.")
    public void submitEmptyFormTest() throws Exception {
        var emptyUser = new User("", "", "");

        newUserPage.fulfilForm(emptyUser)
                .wait.until(
                ExpectedConditions.visibilityOf(newUserPage.error.emailError));

        Assertions.assertThat("Required")
                .isEqualTo(newUserPage.error.nameError.getText())
                .isEqualTo(newUserPage.error.emailError.getText())
                .isEqualTo(newUserPage.error.passwordError.getText());

        verifyNoUserWasCreated();
    }

//    @Test
    @DisplayName("Create a user with password repeat NOT the same as the password")
    public void incorrectPasswordRepeatTest() throws Exception {
        var user = new User(getRandomName(), getRandomEmail(), getRandomPassword());
        user.setConfirmationPassword(user.password + "incorrect");

        newUserPage.fulfilForm(user)
                .wait.until(
                ExpectedConditions.visibilityOf(newUserPage.error.confirmationPasswordError));

        Assertions.assertThat("passwords are not the same")
                .isEqualTo(newUserPage.error.confirmationPasswordError.getText());

        verifyNoUserWasCreated();
    }

//    @Test
    @DisplayName("Create a user with a password shorter than 6")
    public void tooShortPasswordTest() throws Exception {
        var user = new User(getRandomName(), getRandomEmail(), getRandomString(1, 5));

        newUserPage.fulfilForm(user)
                .wait.until(
                ExpectedConditions.visibilityOf(newUserPage.error.passwordError));

        Assertions.assertThat("Minimum size is 6")
                .isEqualTo(newUserPage.error.passwordError.getText());

        verifyNoUserWasCreated();
    }

    /*the local part can have these ASCII characters:
    lowercase Latin letters: abcdefghijklmnopqrstuvwxyz
    uppercase Latin letters: ABCDEFGHIJKLMNOPQRSTUVWXYZ
    digits: 0123456789
    special characters: !#$%&'*+-/=?^_`{|}~
    dot: . (not first or last character or repeated unless quoted)
    space punctuations such as: "(),:;<>@[\] (with some restrictions)
    comments: () (are allowed within parentheses, e.g. (comment)john.smith@example.com).*/

    public class InvalidEmail extends BaseTest{
        @ParameterizedTest(name = "{index} Create a user with an invalid email address - [{arguments}]")
        @ValueSource(strings = {
                ".",
//                "plainaddress",
//                "@.",
//                "@missing.username",
//                "missing_domain@.",
//                ".dotOnBeginning@gmail.com",
//                "comma,@gmail.com",
//                "two..dotsInRow@gmail.com",
//                "two@At@characters.com",
//                "あいうえお@unicodeChars.com",
                "leadingDashs@-inDomain.com"})
        public void invalidEmailTest(String invalidEmail) throws Exception {
            var user = new User(getRandomName(), invalidEmail, getRandomPassword());

            newUserPage.fulfilForm(user)
                    .wait.until(
                    ExpectedConditions.visibilityOf(newUserPage.error.emailError));

            Assertions.assertThat("Invalid email address")
                    .isEqualTo(newUserPage.error.emailError.getText());

            verifyNoUserWasCreated();
        }
    }

    @Step("Verify no users were created")
    private void verifyNoUserWasCreated() throws Exception {
        newUserPage.goToAllUsers();
        Verifier.verifyIfUsersCreatedAreTheSameAsProvided(
                null, userService.getAllUsers(), allUsersPage.getAllUsers());
    }
}
