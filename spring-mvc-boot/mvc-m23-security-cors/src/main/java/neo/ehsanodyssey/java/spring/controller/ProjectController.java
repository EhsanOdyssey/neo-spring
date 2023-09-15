package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
// Granular CORS Configuration on Controller class
@CrossOrigin(origins="http://otherdomain.com")
public class ProjectController {

	@Autowired
	public ProjectService projectService;

	@ResponseBody
	@RequestMapping(value="/find/{projectId}")
	// Granular CORS Configuration on endpoint method level
	@CrossOrigin(origins="http://anotherdomain.com", methods=RequestMethod.POST)
	public Project findProjectObject(@PathVariable Long projectId) {
		return this.projectService.find(projectId);
	}
}
