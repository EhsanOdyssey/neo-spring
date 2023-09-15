package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	public ProjectService projectService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProject(Model model){

		model.addAttribute("types", new ArrayList<String>(){{
			add("");
			add("Single Year");
			add("Multi Year");
		}});

		model.addAttribute("project", new Project());

		return "project_add";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String saveProject(@ModelAttribute Project project) {
		System.out.println("invoking save project");
		System.out.println(project);
		return "project_add";
	}
}
