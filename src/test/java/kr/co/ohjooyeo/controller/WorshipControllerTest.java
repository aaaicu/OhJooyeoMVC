package kr.co.ohjooyeo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/spring-security.xml"})
@WebAppConfiguration
public class WorshipControllerTest {
//	@Mock
//	WorshipService worshipService;
//	
//	@InjectMocks
//	WorshipController worshipController;
	
	@Autowired
    private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testWorshipList() throws Exception {
		this.mockMvc.perform(get("/worship/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"churchId\":\"1\"}"))
//		.andDo(print())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").exists())
		
		;
	}
	
	@Test
	public void testWorshipInfo() throws Exception {
		this.mockMvc.perform(post("/worship/info")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"churchId\":\"1\",\"worshipId\":\"19-001\"}"))
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.worshipOrder").exists())
//		.andDo(print())
		;
	}
	
	
	
	
	
}