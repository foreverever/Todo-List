package programmers.domain.todo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TodoTest {

    private Todo originTodo;
    private Todo updatedTodo;
    private Todo defaultTodo;

    @Before
    public void setUp() throws Exception {
        originTodo = new Todo("Title","Contents","high", LocalDateTime.now());
        updatedTodo = new Todo("updatedTitle","updatedContets","low",LocalDateTime.now());
        defaultTodo = new Todo();
    }

    @Test
    public void update_success() {
        originTodo.update(updatedTodo);
        assertThat(originTodo.getTitle()).isEqualTo(updatedTodo.getTitle());
        assertThat(originTodo.getContents()).isEqualTo(updatedTodo.getContents());
    }

    @Test
    public void complete_success() {
        originTodo.complete();
        assertThat(originTodo.isCompleted()).isTrue();
    }

    @Test
    public void updateCurrentTime_expired() {
        originTodo.updateCurrentTime();
        assertThat(originTodo.isExpired()).isTrue();
    }

    @Test
    public void updateCurrentTime_not_expired() {
        LocalDateTime tempDataTime = LocalDateTime.of(2019,12,25,10,30);
        originTodo.setDeadline(tempDataTime);
        originTodo.updateCurrentTime();
        assertThat(originTodo.isExpired()).isFalse();
    }

    @Test
    public void defaultTodo_deadline_null_check() {
        assertThat(defaultTodo.getDeadline()).isEqualTo(null);
    }
}
