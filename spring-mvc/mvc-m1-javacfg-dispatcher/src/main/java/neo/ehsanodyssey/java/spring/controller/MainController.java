package neo.ehsanodyssey.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main")
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String message() {
        return "Hello Spring MVC Dispatcher Servlet";
    }
}
