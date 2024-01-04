package br.com.car.rental.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthenticationRestControllerTest { // extends BaseRestControllerTest<User> {
//	@Autowired
//    private MockMvc mvc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Empty
	}

	@BeforeEach
	void setUp() throws Exception {
		// Empty
	}

//	private String getToken(LoginDto loginDto) throws Exception {
//		MvcResult result = mvc.perform(MockMvcRequestBuilders
//				.get("/api/signin")
//				.servletPath("/api/signin")
//				.content(toJson(loginDto))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andReturn();
//		
//		return result.getResponse().getContentAsString();
//	}
	
	@Test
	void testSignin() throws Exception {
//		String token = getToken(new LoginDto("pinkman", "h3ll0"));
//		assertNotNull(token);
	}

	@Test
	void testSigninInvalidLogin() throws Exception {
//		MvcResult result = mvc.perform(MockMvcRequestBuilders
//				.get("/api/signin")
//				.servletPath("/api/signin")
//				.content(toJson(new LoginDto("invalid", "h3ll0")))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest())
//				.andReturn();
//		
//		String contentAsString = result.getResponse().getContentAsString();
//		
//		assertNotNull(contentAsString);
	}

	@Test
	void testSigninInvalidPassword() throws Exception {
//		MvcResult result = mvc.perform(MockMvcRequestBuilders
//				.get("/api/signin")
//				.servletPath("/api/signin")
//				.content(toJson(new LoginDto("pinkman", "invalid")))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest())
//				.andReturn();
//
//		String contentAsString = result.getResponse().getContentAsString();
//
//		assertNotNull(contentAsString);
	}

	@Test
	void testMe() throws Exception {
//		String token = getToken(new LoginDto("pinkman", "h3ll0"));
//		MvcResult result = mvc.perform(MockMvcRequestBuilders
//	            .get("/api/me")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .header("Authorization", "Bearer " + token))
//	            .andExpect(status().isOk())
//	            .andReturn();
//		String userJson = result.getResponse().getContentAsString();
//		assertNotNull(userJson);
	}
}
