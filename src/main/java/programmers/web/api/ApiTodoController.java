package programmers.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmers.domain.todo.Todo;
import programmers.service.TodoService;

@RestController
@RequestMapping("/api/todos/{id}")
public class ApiTodoController {

    @Autowired
    private TodoService todoService;

    @PutMapping("")
    public ResponseEntity update(@PathVariable long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.update(id, todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity delete(@PathVariable long id){
        todoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity complete(@PathVariable long id) {
        Todo currentTodo = todoService.complete(id);
        return new ResponseEntity<Todo>(currentTodo, HttpStatus.OK);
    }
}
