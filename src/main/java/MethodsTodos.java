import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MethodsTodos {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static List<Todos> getOpenTasks(URI uri, int userId) throws IOException, InterruptedException {
        uri = URI.create(uri + "/users/" + userId + "/todos");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<Todos> posts = GSON.fromJson(response.body(), new TypeToken<List<Todos>>() {
        }.getType());
        for (Todos a : posts) {
            if (a.getCompleted().equals("false")) {
                System.out.println("Open Task = " + a);
            }
        }
        System.out.println(response.statusCode());
        return posts;
    }
}
