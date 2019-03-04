package models;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.DeserializationFeature;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.pro.shaded.org.apache.http.client.methods.HttpDelete;
import io.cucumber.pro.shaded.org.apache.http.client.methods.HttpGet;
import io.cucumber.pro.shaded.org.apache.http.client.methods.HttpPost;
import io.cucumber.pro.shaded.org.apache.http.client.methods.HttpUriRequest;
import io.cucumber.pro.shaded.org.apache.http.client.utils.URIBuilder;
import io.cucumber.pro.shaded.org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.io.IOUtils;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class UserService {
    String url;

    public UserService(String url) {
        this.url = url;
    }

    public Object deleteAllUsers() throws Exception {
        String endpoint = "/user/all";

        HttpUriRequest request = new HttpDelete(new URI(url + endpoint));
        return HttpClientBuilder.create().build().execute(request);
    }

    public List<User> getAllUsers() throws Exception {
        String endpoint = "/user/all/json";

        HttpUriRequest request = new HttpGet(new URI(url + endpoint));
        String response = IOUtils.toString(
                HttpClientBuilder.create().build().execute(request)
                        .getEntity().getContent(), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        var users = mapper.readValue(response, User[].class);
        if (users.length == 0) {
            return null;
        }
        return Arrays.asList(users);
    }

    public Object postUser(User user) throws Exception {
        String endpoint = "/user/save";

        URIBuilder builder = new URIBuilder(new URI(url + endpoint))
                .addParameter("user.name", user.name)
                .addParameter("user.email", user.email)
                .addParameter("user.password", user.password)
                .addParameter("confirmationPassword", user.confirmationPassword);

        HttpUriRequest request = new HttpPost(builder.build());
        return HttpClientBuilder.create().build().execute(request);
    }
}
