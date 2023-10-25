package br.com.car.rental.api.data;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.car.rental.model.CarColor;
import br.com.car.rental.shared.validation.IsBeforeOrEqualThanCurrentYear;
import br.com.car.rental.shared.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRequestDto(
		@NotNull @IsBeforeOrEqualThanCurrentYear @JsonProperty("year") Integer productionYear,
		@NotBlank @NotNull @Length(max = 10) String licensePlate,
		@NotBlank @NotNull @Length(min = 5, max = 60) String model,
		@ValueOfEnum(enumClass = CarColor.class) String color) {

}
