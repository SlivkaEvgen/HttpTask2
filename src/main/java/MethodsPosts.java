import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MethodsPosts {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static int idPost;

    public static List<UserPost> getUserPosts(URI uri, int userId) throws IOException, InterruptedException {
        uri = URI.create(uri + "/users/" + userId + "/posts");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        List<UserPost> posts = GSON.fromJson(response.body(), new TypeToken<List<UserPost>>() {
        }.getType());
        for (UserPost a : posts) {
            idPost = a.getId();
        }
        System.out.println(response.statusCode());
        return posts;
    }

    public static List<Posts> getPosts(URI uri, int id) throws IOException, InterruptedException {
        uri = URI.create(uri + "/posts/" + id + "/comments");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Posts> posts = GSON.fromJson(response.body(), new TypeToken<List<Posts>>() {
        }.getType());
        System.out.println(response.statusCode());
        return posts;
    }

    public static List<Posts> AllPosts(URI uri, int userId) throws IOException, InterruptedException {
        getUserPosts(URI.create(uri + ""), userId);
        List<Posts> postsList = getPosts(URI.create(uri + ""), idPost);
        String json;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(postsList);
        System.out.println(json);
        File file = new File("src/main/resources/user-" + userId + "-post-" + idPost + "-comments.json");
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(json));
        writer.flush();
        writer.close();
        return postsList;
    }
}
