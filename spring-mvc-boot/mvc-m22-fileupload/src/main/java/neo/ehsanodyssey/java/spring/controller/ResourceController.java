package neo.ehsanodyssey.java.spring.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import neo.ehsanodyssey.java.spring.entities.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@RequestMapping(value="/upload", method= RequestMethod.POST)
	@ResponseBody
	public String handleUpload(@RequestParam("file") MultipartFile file) {

		if(!file.isEmpty()) {
			return "The file size is: " + file.getSize();
		} else {
			return "The file is empty";
		}
	}

	@RequestMapping("/add")
	public String add(Model model) {
		System.out.println("Invoking the add method");
		return "resource_add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		System.out.println(resource);
		System.out.println("Invoking the save method");
		status.setComplete();
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
