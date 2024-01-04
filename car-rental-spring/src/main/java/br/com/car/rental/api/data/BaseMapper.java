package br.com.car.rental.api.data;

import br.com.car.rental.model.CarColor;

/**
 * Class to map the User entity to the UserRequestDTO and vice-versa.
 * ModelMapper currently does not support record types.
 */
public abstract class BaseMapper {
	protected CarColor convertColorValue(String value) {
		CarColor toReturn = null;
		if (value == null) {
			return null;
		}
		toReturn = CarColor.valueOf(value);
		if (toReturn == null) {
			throw new IllegalArgumentException("Invalid Color.");
		}
		return toReturn;
	}
}
