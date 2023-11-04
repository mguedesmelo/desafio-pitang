package br.com.car.rental.api.data;

import java.time.LocalDate;

/**
 * Used as response object that represents a User
 */
public record UserDto(
		Long id, 
		String firstName, 
		String lastName, 
		String email, 
		LocalDate birthDay, 
		String login, 
		String phone
		) {
}
