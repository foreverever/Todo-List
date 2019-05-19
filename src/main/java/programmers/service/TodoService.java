package programmers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.domain.todo.Todo;
import programmers.domain.todo.TodoRepository;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Transactional
    public Todo add(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional
    public Todo update(long id, Todo updatedTodo) {
        Todo currentTodo = findTodo(id);
        return currentTodo.update(updatedTodo);
    }

    @Transactional
    public void delete(long id) {
        Todo currentTodo = findTodo(id);
        todoRepository.delete(currentTodo);
    }

    @Transactional
    public Todo complete(long id) {
        Todo currentTodo = findTodo(id);
        return currentTodo.complete();
    }

    @Transactional
    public void updateCurrentTime(List<Todo> todos) {
        for (Todo todo : todos) {
            todo.updateCurrentTime();
        }
    }

    public Todo findTodo(long id) {
        return todoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}