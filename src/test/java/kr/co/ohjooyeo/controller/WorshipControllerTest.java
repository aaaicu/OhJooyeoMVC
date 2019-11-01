package kr.co.ohjooyeo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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
		this.mockMvc.perform(post("/worship/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"churchId\":\"1\"}"))
//		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").exists())
		;
	}
	
	@Test
	public void testWorshipInfo() throws Exception {
		this.mockMvc.perform(post("/worship/info")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"churchId\":\"1\",\"worshipId\":\"19-001\",\"version\" : 0}"))
//		.andDo(print())
		.andExpect(status().isOk())
//		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.nextPresenter").exists())
		.andExpect(jsonPath("$.mainPresenter").exists())
		.andExpect(jsonPath("$.worshipOrder").exists())
		;
	}
	
	@Test 
	@Transactional
	@Rollback(true)
    public void testWorshipAdd() throws Exception{
		this.mockMvc.perform(post("/worship/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ "
						+ 	"\"worshipInfo\" :  "
						+ "{\"churchId\" : 1,"
						+	"\"worshipDate\" : \"2019-10-19\","
						+ 	"\"mainPresenter\" : \"사회자\","
						+ 	"\"nextPresenter\" : \"다음사회자\","
						+ 	"\"nextPrayer\" : \"다음기도자\","
						+ 	"\"nextOffer\" : \"다음봉헌자\""
						+ "},"
						+ "\"worshipOrder\" : "
						+ "[{ "
						+ 	"\"type\": 0," 
						+ 	"\"standupYn\": 0," 						
						+ 	"\"title\": \"경배와 찬양\"," 
						+ 	"\"detail\": \"\" ," 
						+	"\"presenter\": \"헤세드 찬양단\"," 
						+ 	"\"order\": 1," 
						+ 	"\"orderId\": 1" 
						+ "},"
						+ "{ "
						+ 	"\"type\": 0," 
						+ 	"\"standupYn\": 0," 		
						+ 	"\"title\": \"말씀\"," 
						+ 	"\"detail\": \"행복의 노래\" ," 
						+	"\"presenter\": \"강인호 목사님\"," 
						+ 	"\"order\": 2," 
						+ 	"\"orderId\": 2" 
						+ "}],"
						+ "\"worshipAd\" : "
						+ "[{ "
						+ 	"\"title\": \"환영\","
						+	"\"content\": \"돈암동교회 청년부에 오신 여러분 환영합니다.\"," 
						+ 	"\"order\": 1," 
						+ 	"\"adId\": 1" 
						+ "},"
						+ "{ "
						+ 	"\"title\": \"1330 기도회\"," 
						+	"\"content\": \"예배전 기도회가 있어요ㅎㅎㅎ\"," 
						+ 	"\"order\": 2," 
						+ 	"\"adId\": 2" 
						+ "}]"
						+ "}"))
		.andDo(print())
		.andExpect(status().isOk());
    }
	
	public void testWorshipOrderAdd() {
		
	}
	
	
	
	
	
	
	
}
