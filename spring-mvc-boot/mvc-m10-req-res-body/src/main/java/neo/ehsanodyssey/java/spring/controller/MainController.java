package neo.ehsanodyssey.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {

	@RequestMapping("/")
	public String greeting(Model model) {

		return "home";
	}
	
}
