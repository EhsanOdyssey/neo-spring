package neo.ehsanodyssey.java.spring.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import neo.ehsanodyssey.java.spring.entities.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@ResponseBody
	@RequestMapping("/request")
	public String request(@RequestBody String resource) {
		System.out.println(resource);
		//Send out an email
		return "The request has been sent for approval";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource) {
		System.out.println("Invoking the review method.");
		return "resource_review";
	}
	
	@RequestMapping("/add")
	public String add() {
		System.out.println("Invoking the add method");
		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		System.out.println(resource);
		System.out.println("Invoking the save method");
		status.setComplete(); // Reset session values
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
