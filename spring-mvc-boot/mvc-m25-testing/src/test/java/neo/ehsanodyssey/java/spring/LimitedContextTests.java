package neo.ehsanodyssey.java.spring;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import neo.ehsanodyssey.java.spring.controller.ProjectController;
import neo.ehsanodyssey.java.spring.entities.Project;
import neo.ehsanodyssey.java.spring.services.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectController.class)
public class LimitedContextTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService service;
	
	@Test
	public void test() throws Exception {

		when(service.find(1L)).thenReturn(new Project(1L, "Java Project", "This is a simple description"));
		
		this.mockMvc.perform(get("/project/find/1")).andDo(print())
				.andExpect(content().string(containsString("Java Project")));

	}

}
