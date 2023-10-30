package br.com.car.rental.api.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.api.data.UserRequestDto;

public class BaseRestControllerTest<T> {
	protected UserRequestDto createUserSkyler() {
		List<CarRequestDto> carsSkyler = Arrays
				.asList(new CarRequestDto(1991, "QHU 6881", "Jeep Grand Wagoneer", "White"));

		UserRequestDto userSkyler = new UserRequestDto("Skyler", "White", "skyler@somedomain.com",
				LocalDate.of(1958, 9, 7), "skyler", "+1 515-516-0624",
				"$2a$10$SDelfowfCRWei0rkUI5IIO1dLNKYrcHP4cjbjoJLViYj4h/0a7VdO", carsSkyler);
		return userSkyler;
	}

	protected String toJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected T toModel(String jsonString, Class<T> valueType) throws Exception {
		return new ObjectMapper().readValue(jsonString, valueType);
	}
}
