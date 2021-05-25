import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Methods {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();


    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), User.class);
    }

    public static User getById(URI uri, int id) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final User user = GSON.fromJson(response.body(), User.class);
        System.out.println(response.statusCode());
        return user;
    }

    public static List<User> getUserName(URI uri, String userName) throws IOException, InterruptedException {
        uri = URI.create(uri + "?username=" + userName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        System.out.println(response.statusCode());
        return users;
    }

    public static List<User> getAllUsers(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        System.out.println(response.statusCode());
        return users;
    }

    public static void delete(URI uri, int id) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(uri + "/" + id))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(send);

    }

    public static User getChange(URI uri, User user) throws IOException, InterruptedException {
        user.setName("Novoe Imia");
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return user;
    }
}
