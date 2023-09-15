package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	public ProjectService projectService;

	// we can use below endpoints with below formats:
	// /content-negotiation/project/1.json
	// /content-negotiation/project/1.xml
	// /content-negotiation/project/find/1.json
	// /content-negotiation/project/find/1.xml
	// /content-negotiation/project/find.json
	// /content-negotiation/project/find.xml
	// /content-negotiation/project/find/1?accept=xml
	// /content-negotiation/project/find/1?accept=json

	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable Long projectId) {
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}

	@ResponseBody
	@RequestMapping(value="/find/{projectId}")
	public Project findProjectObject(@PathVariable Long projectId) {
		return this.projectService.find(projectId);
	}
	
	@RequestMapping(value="/find")
	public String find(Model model) {
		model.addAttribute("projects", this.projectService.findAll());
		return "projects";
	}
}
