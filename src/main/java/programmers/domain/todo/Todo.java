package programmers.domain.todo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 100)
    private String contents;


    @Column(nullable = false, length = 10)
    private String priority;

    private boolean completed;
    private LocalDateTime deadline;

    public Todo() {
        completed = false;
    }

    public Todo(String title, String contents, String priority, LocalDateTime deadline) {
        this.title = title;
        this.contents = contents;
        this.priority = priority;
        this.deadline = deadline;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                completed == todo.completed &&
                Objects.equals(title, todo.title) &&
                Objects.equals(contents, todo.contents) &&
                Objects.equals(priority, todo.priority) &&
                Objects.equals(deadline, todo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, priority, completed, deadline);
    }
}
