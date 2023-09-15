package neo.ehsanodyssey.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {

    @ModelAttribute("currentDate")
    public Date getCurrentDate() {
        return new Date();
    }

    // Global @ExceptionHandler for all controllers
    @ExceptionHandler(value=NullPointerException.class)
    public String handleError() {
        return "controller_error";
    }
}
