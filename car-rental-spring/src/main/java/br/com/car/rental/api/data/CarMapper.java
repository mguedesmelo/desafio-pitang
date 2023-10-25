package br.com.car.rental.api.data;

import org.springframework.stereotype.Component;

import br.com.car.rental.model.Car;

/**
 * Class to map the User entity to the UserRequestDTO and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class CarMapper extends BaseMapper {
	public CarDto map(Car car) {
		if (car == null) {
			return null;
		}
		return new CarDto(car.getId(), car.getProductionYear(), car.getLicensePlate(), 
				car.getModel(), car.getColor().toString());
	}

	public Car toModel(CarRequestDto carRequestDto) {
		Car carToReturn = new Car();
		carToReturn.setProductionYear(carRequestDto.productionYear());
		carToReturn.setLicensePlate(carRequestDto.licensePlate());
		carToReturn.setModel(carRequestDto.model());
		carToReturn.setColor(convertColorValue(carRequestDto.color()));

		return carToReturn;
	}
}