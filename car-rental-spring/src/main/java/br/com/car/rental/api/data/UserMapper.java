package br.com.car.rental.api.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.car.rental.model.Car;
import br.com.car.rental.model.CarColor;
import br.com.car.rental.model.User;

/**
 * Class to map the User entity to the UserRequestDTO and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class UserMapper {
	public UserDto map(User user) {
		if (user == null) {
			return null;
		}
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDay(),
				user.getLastName(), user.getPhone());
	}

	public User toModel(UserRequestDto userRequestDto) {
		User user = new User();
		user.setFirstName(userRequestDto.firstName());
		user.setLastName(userRequestDto.lastName());
		user.setEmail(userRequestDto.email());
		user.setBirthDay(userRequestDto.birthDay());
		user.setLogin(userRequestDto.login());
		user.setPhone(userRequestDto.phone());
		user.setPassword(userRequestDto.password());

		List<Car> cars = userRequestDto.cars().stream().map(carRequestDto -> {
			Car car = new Car();
			// FIXME Analisar se isto eh necessario
//			if (car.getId() > 0) {
//				car.setId(carDto.id());
//			}
			car.setProductionYear(carRequestDto.productionYear());
			car.setLicensePlate(carRequestDto.licensePlate());
			car.setModel(carRequestDto.model());
			car.setColor(convertColorValue(carRequestDto.color()));
			car.setProductionYear(carRequestDto.productionYear());
			car.setUser(user);

			return car;
		}).collect(Collectors.toList());
		user.setCars(cars);

		return user;
	}

	private CarColor convertColorValue(String value) {
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