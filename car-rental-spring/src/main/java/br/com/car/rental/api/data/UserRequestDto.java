package br.com.car.rental.api.data;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.car.rental.shared.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record UserRequestDto(
		@NotBlank 
		@NotNull 
		@Length(min = 5, max = 60) 
		String firstName,
		
		@NotBlank 
		@NotNull 
		@Length(min = 5, max = 60) 
		String lastName,

		@NotBlank 
		@NotNull 
		@Email 
		String email,

		@NotNull 
		@Past
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT_ISO8601)
		LocalDate birthDay, 

		@NotBlank 
		@NotNull 
		@Length(min = 5, max = 20) 
		String login,

		@NotBlank 
		@NotNull 
		@Length(max = 20) 
		String phone,

		@NotBlank 
		@NotNull 
		String password,

		@Valid 
		List<CarRequestDto> cars
		) {
}
