import java.util.Objects;

public class Todos {
    private Integer userId;
    private Integer id;
    private String title;
    private String completed;

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
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todos todos = (Todos) o;
        return userId.equals(todos.userId) && id.equals(todos.id) && title.equals(todos.title) && completed.equals(todos.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, completed);
    }

    @Override
    public String toString() {
        return "Todos{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }
}
