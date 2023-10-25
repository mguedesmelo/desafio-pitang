package br.com.car.rental.api.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.car.rental.model.Car;
import br.com.car.rental.model.User;

/**
 * Class to map the User entity to the UserRequestDTO and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class UserMapper extends BaseMapper {
	public UserDto map(User user) {
		if (user == null) {
			return null;
		}
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDay(),
				user.getLastName(), user.getPhone());
	}

	public User toModel(UserRequestDto userRequestDto) {
		User userToReturn = new User();
		userToReturn.setFirstName(userRequestDto.firstName());
		userToReturn.setLastName(userRequestDto.lastName());
		userToReturn.setEmail(userRequestDto.email());
		userToReturn.setBirthDay(userRequestDto.birthDay());
		userToReturn.setLogin(userRequestDto.login());
		userToReturn.setPhone(userRequestDto.phone());
		userToReturn.setPassword(userRequestDto.password());

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
			car.setUser(userToReturn);

			return car;
		}).collect(Collectors.toList());
		userToReturn.setCars(cars);

		return userToReturn;
	}
}