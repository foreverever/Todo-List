package programmers.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public String updateForm(@PathVariable long id, Model model) {
        System.out.println("id!!!!!!!!!!!!!!!!! : " + id);
        model.addAttribute("todo", todoService.findTodo(id));
        return "/todo/updateForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable long id, Todo updatedTodo) {
        todoService.update(id, updatedTodo);
        return "redirect:/";
    }
}
