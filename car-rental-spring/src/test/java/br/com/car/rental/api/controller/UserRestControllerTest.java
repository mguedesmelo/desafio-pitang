/**
 * 
 */
package br.com.car.rental.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.car.rental.api.data.UserDto;
import br.com.car.rental.api.data.UserRequestDto;
import br.com.car.rental.model.User;

/**
 * @author Marcio Guedes <mailto:mguedesmelo@gmail.com>
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserRestControllerTest extends BaseRestControllerTest<User> {
	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
    private MockMvc mvc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Empty
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Empty
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#findAll()}.
	 */
	@Test
	void testFindAll() {
		UserDto[] users = this.testRestTemplate.getForObject("/api/users", UserDto[].class);
		assertNotNull(users);
		if (users != null) {
			assertTrue(users.length > 0);
		}
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#save(br.com.car.rental.api.data.UserRequestDto)}.
	 */
	@Test
	void testSave() {
		UserRequestDto userSkyler = createUserSkyler();
		UserDto user = this.testRestTemplate.postForObject("/api/users", userSkyler, UserDto.class);
		assertNotNull(user);
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#save(br.com.car.rental.api.data.UserRequestDto)}.
	 */
	@Test
	void testSaveEmailAlreadyExists() {
		UserRequestDto userSkyler = createUserSkyler();
		UserDto user = this.testRestTemplate.postForObject("/api/users", userSkyler, UserDto.class);
		assertNotNull(user);
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#findById(java.lang.Long)}.
	 */
	@Test
	void testFindById() {
		UserDto user = this.testRestTemplate.getForObject("/api/users/1", UserDto.class);
		assertNotNull(user);
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#delete(java.lang.Long)}.
	 */
	@Test
	void testDelete()  throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.delete("/api/users/{id}", "1")
				.servletPath("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	/**
	 * Test method for {@link br.com.car.rental.api.controller.UserRestController#update(java.lang.Long, br.com.car.rental.api.data.UserRequestDto)}.
	 */
	@Test
	void testUpdate() throws Exception {
		UserDto user = this.testRestTemplate.getForObject("/api/users/1", UserDto.class);
		mvc.perform(MockMvcRequestBuilders
	              .put("/api/users/{id}", "2")
	              .servletPath("/api/users/1")
	              .content(toJson(user))
	              .contentType(MediaType.APPLICATION_JSON)
	              .accept(MediaType.APPLICATION_JSON))
	              .andExpect(status().isOk());
	}
}
