package neo.ehsanodyssey.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model) {
		System.out.println("Invoking the add method");

		// Case 1: handling the exception by @ExceptionHandler because of NullPointerException that will handled by handleError() method
		if(1 == 1) {
			throw new NullPointerException("This is an exception");
		}

		// Case 2: handling the exception by GlobalHandlerExceptionResolver class because of RuntimeException that is parent of NullPointerException, so GlobalHandlerExceptionResolver will handle it
		if(1 == 1) {
			throw new RuntimeException("This is an exception");
		}
		
		return "resource_add";
	}

	// Below Handler is used for exceptions instance of NullPointerException
	// we can use the HttpServletRequest as a parameter for this method
	@ExceptionHandler(value=NullPointerException.class)
	public String handleError() {
//	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
}
