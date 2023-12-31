package neo.ehsanodyssey.java.spring;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContextOnlyTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void postTest() throws Exception {

		this.mockMvc.perform(post("/project/add")
				.param("name", "Java Project")
				.param("description", "Over 5"))
				.andDo(print()).andExpect(redirectedUrl("/home/"));
	}

	@Test
	public void getTest() throws Exception {

		this.mockMvc.perform(get("/project/find/1")).andDo(print())
				.andExpect(content().string(containsString("Java Project")));

	}

}
