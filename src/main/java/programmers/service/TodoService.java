package programmers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.domain.todo.Todo;
import programmers.domain.todo.TodoRepository;

import java.time.LocalDateTime;
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
    public void findDeadlineTodo(LocalDateTime now) {
        List<Todo> notExpiredTodos = todoRepository.findByExpiredAndDeadlineBefore(false, now);
        for (Todo todo : notExpiredTodos) {
            todo.updateExpired();
        }
    }

    public Todo findTodo(long id) {
        return todoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}