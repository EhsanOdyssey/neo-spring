package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import neo.ehsanodyssey.java.spring.validators.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	public ProjectService projectService;

	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable Long projectId) {
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}

	@RequestMapping(value="/find")
	public String find(Model model) {
		model.addAttribute("projects", this.projectService.findAll());
		return "projects";
	}

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
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors) {
		System.out.println("invoking save project");

		if(!errors.hasErrors()) {
			System.out.println("The project validated.");
			System.out.println(project);
		}else {
			System.out.println("The project did not validate.");
		}

		return "project_add";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProjectValidator());
	}
}
