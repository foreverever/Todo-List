package programmers.domain.todo;

import org.junit.Test;

import java.time.LocalDateTime;

public class TodoTest {

    public static final Todo originTodo = new Todo(1L,"Title","Contents","high",false, LocalDateTime.now());

    @Test
    public void update_success() {
        
    }

    @Test
    public void update_fail() {
        
    }

    @Test
    public void delete_success() {

    }

    @Test
    public void delete_fail() {

    }

}
