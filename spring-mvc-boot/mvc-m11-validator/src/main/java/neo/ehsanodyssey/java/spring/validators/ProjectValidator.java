package neo.ehsanodyssey.java.spring.validators;

import neo.ehsanodyssey.java.spring.entities.Project;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Project project = (Project) target;
		
		if(project.getName().length() < 5) {
			errors.rejectValue("name", "project.name", "The name is too short.");
		}

	}

}
