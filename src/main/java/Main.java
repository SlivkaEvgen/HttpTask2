import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String url = "https://jsonplaceholder.typicode.com/users";
        final String urlPosts = "https://jsonplaceholder.typicode.com";
        List<User> userList = Methods.getAllUsers(URI.create(url));

        // new User
        User user = getUser();
        final User createUser = Methods.sendPost(URI.create(url), user);
        userList.add(createUser);
        System.out.println("New User + size = " + userList.size() + ", " + createUser);

        //by ID
        User userById = Methods.getById(URI.create(url), 8);
        System.out.println("User by ID-8 = " + userById);

        //by UserName
        userList = Methods.getUserName(URI.create(url), "Moriah.Stanton");
        System.out.println("User username/Moriah.Stanton = " + userList);

        // all Users
        userList = Methods.getAllUsers(URI.create(url));
        System.out.println("All Users = " + userList);
        //  System.out.println("All Users = " + userList.size());

        // Delete user
        Methods.delete(URI.create(url), 8);

        //Change usere
        User changeUser = Methods.getChange(URI.create(url), userById);
        System.out.println("Change user = " + changeUser);

        //Posts by UserID
        List<UserPost> userPosts = MethodsPosts.getUserPosts(URI.create(urlPosts), 2);
        System.out.println("User posts by UserID = " + userPosts);

        //Posts by PostId
        List<Posts> postsList = MethodsPosts.getPosts(URI.create(urlPosts), 10);
        System.out.println("Posts by PostId = " + postsList);

        // Last Posts by UserID...+ write file.json
        List<Posts> postsList1 = MethodsPosts.AllPosts(URI.create(urlPosts), 3);
        System.out.println("Last Posts by UserID = " + postsList1);

        // Todos
        List<Todos> todosList = MethodsTodos.getOpenTasks(URI.create(urlPosts), 2);
        System.out.println("Todos list = " + todosList);

    }

    private static User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("Ivan");
        user.setUsername("Javaskin");
        user.setEmail("Javaskin@mail.com");
        return user;
    }

}
