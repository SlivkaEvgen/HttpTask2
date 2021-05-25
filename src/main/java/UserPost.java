import java.util.Objects;

public class UserPost {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPost post = (UserPost) o;
        return userId.equals(post.userId) && id.equals(post.id) && title.equals(post.title) && body.equals(post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", Id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
