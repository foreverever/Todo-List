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

    public Todo add(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    @Transactional
    public Todo update(long id, Todo updatedTodo) {
        Todo currentTodo = todoRepository.findById(id)
                .orElseThrow(UnknownError::new);    //예외처리 클래스 생성 필요
        return currentTodo.update(updatedTodo);
    }

    public Todo findTodo(long id) {
        return todoRepository.findById(id)
                .orElseThrow(UnknownError::new);
    }

    @Transactional
    public void delete(long id) {
        Todo currentTodo = todoRepository.findById(id)
                .orElseThrow(UnknownError::new);
        todoRepository.delete(currentTodo);
    }

    @Transactional
    public Todo complete(long id) {
        Todo currentTodo = todoRepository.findById(id)
                .orElseThrow(UnknownError::new);
        return currentTodo.complete();
    }
}