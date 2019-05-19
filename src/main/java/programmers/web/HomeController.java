package programmers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import programmers.domain.todo.Todo;
import programmers.service.TodoService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String home(Model model) {
        LocalDateTime now = LocalDateTime.now();
        todoService.findDeadlineTodo(now);

        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "/index";
    }
}
