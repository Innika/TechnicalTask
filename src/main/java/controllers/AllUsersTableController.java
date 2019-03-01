package controllers;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllUsersTableController {
    WebElement tableElement;

    public AllUsersTableController(WebElement tableElement) throws Exception {
        if (tableElement.getAttribute("class").equals("table")) {
            this.tableElement = tableElement;
        } else {
            throw new Exception("The element %s isn't a AllUsersTableController type");
        }
    }

    public List<User> getUsers() {
        var users = new ArrayList<User>();

        List<String> headers = tableElement.findElements(By.cssSelector("th"))
                .stream().map(p -> p.getText()).collect(Collectors.toList());
        var rows = tableElement.findElements(By.cssSelector("tbody tr"));


        rows.forEach(row -> {
            String name = row.findElement(By.xpath(
                    String.format(".//td[%s]", headers.indexOf("Name") + 1)))
                    .getText();
            String email = row.findElement(By.xpath(
                    String.format(".//td[%s]", headers.indexOf("Email") + 1)))
                    .getText();
            String password = row.findElement(By.xpath(
                    String.format(".//td[%s]", headers.indexOf("Password") + 1)))
                    .getText();
            users.add(new User(name, email, password));
        });
        return users;
    }
}
