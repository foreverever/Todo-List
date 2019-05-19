package programmers.domain.todo;

import support.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Todo extends AbstractEntity {

    @Size(min = 1, max = 100)
    @Column(nullable = false)
    private String title;

    @Size(min = 1)
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, length = 10)
    private String priority;

    private boolean completed;
    private boolean expired;

    private LocalDateTime deadline;

    public Todo() {
        completed = false;
        expired = false;
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

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getFormattedDeadline() {
        return getFormattedDate(deadline, "yyyy-MM-dd HH:mm");
    }

    public Todo update(Todo updatedTodo) {
        this.title = updatedTodo.title;
        this.contents = updatedTodo.contents;
        this.priority = updatedTodo.priority;
        return this;
    }

    public Todo complete() {
        completed = true;
        return this;
    }

    public void updateExpired() {
        expired = true;
    }
}
