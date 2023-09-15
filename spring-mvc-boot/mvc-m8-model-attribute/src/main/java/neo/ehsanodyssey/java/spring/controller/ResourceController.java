package neo.ehsanodyssey.java.spring.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import neo.ehsanodyssey.java.spring.entities.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add() {
		System.out.println("Invoking the add method");
		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println("Invoking the save method");
		return "redirect:/resource/add";
	}

	@ModelAttribute("resource")
	public Resource getResource() {
		System.out.println("Adding an attribute to the model");
		return new Resource();
	}

	@ModelAttribute(value = "checkOptions")
	public List<String> getChecks() {
		return new LinkedList<>(Arrays.asList(new String[] { "Lead Time", "Special Rate", "Requires Approval" }));
	}

	@ModelAttribute(value = "radioOptions")
	public List<String> getRadios() {
		return new LinkedList<>(Arrays.asList(new String[] { "Hours", "Piece", "Tons" }));
	}

	@ModelAttribute(value = "typeOptions")
	public List<String> getTypes() {
		return new LinkedList<>(
				Arrays.asList(new String[] { "Material", "Staff", "Other", "Equipment" }));
	}
}
