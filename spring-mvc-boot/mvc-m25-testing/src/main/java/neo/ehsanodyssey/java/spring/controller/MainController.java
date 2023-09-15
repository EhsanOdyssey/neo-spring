package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.entities.Sponsor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {

	@RequestMapping("/")
	public String greeting(Model model) {
		
		Project project = new Project();
		project.setName("First Project");
		project.setSponsor(new Sponsor("NASA", "555-555-5555", "nasa@nasa.com"));
		project.setDescription("This is a simple project sponsored by Nasa");
		
		model.addAttribute("currentProject", project);
		
		return "home";
	}
	
}
