package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.entities.Sponsor;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class MainController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/", params="projectId")
	public String goHomeAgain(Model model, @RequestParam("projectId") Long projectId) {
		model.addAttribute("currentProject", this.projectService.find(projectId));
		return "home";
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		Project project = new Project();
		project.setName("First Project");
		project.setSponsor(new Sponsor("NASA", "555-555-5555", "nasa@nasa.com"));
		project.setDescription("This is a simple project sponsored by Nasa");

		model.addAttribute("currentProject", project);

		return "home";
	}
	
}
