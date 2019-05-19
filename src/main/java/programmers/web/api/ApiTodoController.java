package programmers.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmers.domain.todo.Todo;
import programmers.service.TodoService;
import support.domain.ErrorMessage;

import javax.validation.ConstraintViolationException;
import java.net.URI;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Todo todo) {
        try {
            Todo createdTodo = todoService.add(todo);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/"));
            return new ResponseEntity<Todo>(createdTodo, headers, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("'제목' 혹은 '내용'을 입력해주세요."), HttpStatus.FORBIDDEN);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("'우선순위를 입력해주세요."), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Todo todo) {
        try {
            Todo updatedTodo = todoService.update(id, todo);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/"));
            return new ResponseEntity<Todo>(updatedTodo, headers, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("'제목' 혹은 '내용'을 입력해주세요."), HttpStatus.FORBIDDEN);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("'우선순위를 입력해주세요."), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            todoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("잘 못 누르셨습니다."), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity complete(@PathVariable long id) {
        try {
            Todo currentTodo = todoService.complete(id);
            return new ResponseEntity<Todo>(currentTodo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("잘 못 누르셨습니다."), HttpStatus.FORBIDDEN);
        }
    }
}
