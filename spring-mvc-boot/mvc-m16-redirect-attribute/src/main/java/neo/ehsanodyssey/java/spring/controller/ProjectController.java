package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import neo.ehsanodyssey.java.spring.validators.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProject(Model model){
		System.out.println("invoking add project");
		model.addAttribute("types", new ArrayList(){{
			add("");
			add("Single Year");
			add("Multi Year");
		}});
		
		model.addAttribute("project", new Project());

		return "project_add";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors, RedirectAttributes attributes) {
		System.out.println("invoking save project");

		if (errors.hasErrors()) {
			System.out.println("The project did not validate.");
			return "project_add";
		}
		System.out.println("The project validated.");
		project.setProjectId(55L);
		this.projectService.save(project);
		// With RedirectAttributes as parameters of this method we can send param to redirected page and
		// in redirected page we can get that param by @RequestParam (goto MainController)
		attributes.addAttribute("projectId", project.getProjectId().toString());
		// If we don't use 'redirect:' in return statement (return "home";), after redirecting to home page if we press F5 in keyboard for refreshing
		// browser will ask us to resend post action in previous step 'project_add' page.
		// With below syntax browser will not ask us to resend post action
		return "redirect:/home/";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProjectValidator());
	}
}
