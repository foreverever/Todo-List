package programmers.domain.todo;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TodoTest {

    public static final Todo ORIGIN_TODO = new Todo("Title","Contents","high", LocalDateTime.now());
    public static final Todo UPDATED_TODO = new Todo("updatedTitle","updatedContets","low",LocalDateTime.now());

    @Test
    public void update_success() {
        ORIGIN_TODO.update(UPDATED_TODO);
        assertThat(ORIGIN_TODO.getTitle()).isEqualTo(UPDATED_TODO.getTitle());
        assertThat(ORIGIN_TODO.getContents()).isEqualTo(UPDATED_TODO.getContents());
    }

    @Test
    public void complete_success() {
        ORIGIN_TODO.complete();
        assertThat(ORIGIN_TODO.isCompleted()).isTrue();
    }
}
