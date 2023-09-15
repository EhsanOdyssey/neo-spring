package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {
	
	@RequestMapping("/")
	public String home(Model model, @ModelAttribute("project") Project project) {
		model.addAttribute("currentProject", project);
		return "home";
	}
	
}
