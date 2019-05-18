package programmers.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.service.TodoService;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private static final Logger logger = getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    public String updateForm(@PathVariable long id, Model model) {
        model.addAttribute("todo", todoService.findTodo(id));
        return "/todo/updateForm";
    }
}
