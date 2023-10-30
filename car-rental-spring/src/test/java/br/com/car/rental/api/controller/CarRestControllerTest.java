package br.com.car.rental.api.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.car.rental.model.Car;

/**
 * @author Marcio Guedes <mailto:mguedesmelo@gmail.com>
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CarRestControllerTest extends BaseRestControllerTest<Car> {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Empty
	}

	@BeforeEach
	void setUp() throws Exception {
		// Empty
	}

	@Test
	void testFindAll() {
		fail("testFindAll not yet implemented");
	}

	@Test
	void testSave() {
		fail("testSave not yet implemented");
	}

	@Test
	void testFindById() {
		fail("testFindById not yet implemented");
	}

	@Test
	void testDelete() {
		fail("testDelete not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("testUpdate not yet implemented");
	}
}
