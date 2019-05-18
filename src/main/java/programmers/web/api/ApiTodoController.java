package programmers.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmers.domain.todo.Todo;
import programmers.service.TodoService;

import java.net.URI;

@RestController
@RequestMapping("/api/todos")
public class ApiTodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Todo todo) {
        Todo createdTodo = todoService.add(todo);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<Todo>(createdTodo, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.update(id, todo);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<Todo>(updatedTodo, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        todoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity complete(@PathVariable long id) {
        Todo currentTodo = todoService.complete(id);
        return new ResponseEntity<Todo>(currentTodo, HttpStatus.OK);
    }
}
