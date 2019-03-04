package models;

import java.nio.charset.Charset;
import java.util.Random;

public class User {
    public String name;
    public String email;
    public String password;
    public String confirmationPassword;
    public Long id;

    public User(){}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmationPassword = password;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
        return this;
    }

    public static class CorrectRandomData {
        public static String getRandomString(int minSize, int maxSize) {
            String charset = "abcdefghijklmnopqrstuvwxyz1234567890-_.";
            String rndString = "";
            int size = new Random().nextInt(maxSize - minSize) + minSize;
            for (int i = 0; i < size; i++){
                rndString += charset.toCharArray()[new Random().nextInt(charset.length() - 1)];
            }
            return  rndString;
        }

        public static String getRandomName() {
            return getRandomString(1, 32);
        }

        public static String getRandomPassword() {
            return getRandomString(6, 32);
        }

        public static String getRandomEmail() {
            String pattern = "%s@gmail.com";
            return String.format(pattern, getRandomString(1, 90));
        }
    }
}
