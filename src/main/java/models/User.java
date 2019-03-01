package models;

import java.nio.charset.Charset;
import java.util.Random;

public class User {
    public String name;
    public String email;
    public String password;
    public String confirmationPassword;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmationPassword = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setconfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public static class CorrectRandomData {
        private static String getRandomString(int minSize, int maxSize) {
            int size = new Random().nextInt(maxSize - minSize) + minSize;
            byte[] array = new byte[size];
            new Random().nextBytes(array);
            return new String(array, Charset.forName("UTF-8"));
        }

        public static String getRandomName() {
            return getRandomString(1, 255);
        }

        public static String getRandomPassword() {
            return getRandomString(6, 255);
        }

        public static String getRandomEmail() {
            //TODO
            return "";
        }
    }
}
