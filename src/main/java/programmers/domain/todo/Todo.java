package programmers.domain.todo;

import support.domain.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Todo extends AbstractEntity {

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

    public void update(Todo updatedTodo) {

    }
}
