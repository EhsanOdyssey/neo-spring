package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/")
    public String message(Model model) {
        // add as a attribute to model object
        model.addAttribute("message", this.greetingService.greet());
        // reture name of jsp that is exists in /WEB-INF/views directory
        return "hello";
    }
}
