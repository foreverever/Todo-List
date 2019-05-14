package programmers.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.domain.todo.Todo;
import programmers.service.TodoService;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private static final Logger logger = getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping("/form")
    public String todoForm() {
        return "/todo/form";
    }

    @PostMapping("")
    public String create(Todo todo) {
        logger.debug("create Todo");
        todoService.add(todo);
        return "redirect:/";
    }
}
