package programmers.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import programmers.domain.todo.TodoRepository;
import support.AcceptanceTest;
import support.HtmlFormDataBuilder;

public class TodoAcceptanceTest extends AcceptanceTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void click_todo_add_button() {   //todo 추가 버튼 클릭
        ResponseEntity<String> responseEntity = template().getForEntity("/todos/form", String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void write_todo() { //todo 작성
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodedForm()
                .addParameter("title", "청소하기")
                .addParameter("contents", "안방, 화장실 청소하기")
                .addParameter("priority", "높음")
                .build();

        ResponseEntity<String> responseEntity = template().postForEntity("/todos", request, String.class);

        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(todoRepository.findByTitle("청소하기").isPresent()).isTrue();
        softly.assertThat(responseEntity.getHeaders().getLocation().getPath()).startsWith("/");
    }
}
