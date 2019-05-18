package programmers.web.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import programmers.domain.todo.Todo;
import support.AcceptanceTest;

import java.time.LocalDateTime;

public class ApiTodoAcceptanceTest extends AcceptanceTest {

    private Todo originTodo = new Todo("Title", "Contents", "high", LocalDateTime.now());

    @Before
    public void setUp() throws Exception {
        originTodo.setId(1L);
        originTodo.setCreatedDate(LocalDateTime.now());
        originTodo.setModifiedDate(LocalDateTime.now());
    }

    @Test
    public void create_Todo() {
        ResponseEntity responseEntity =
                template().postForEntity("/api/todos", originTodo, Todo.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
