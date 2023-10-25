package br.com.car.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.car.rental.api.data.CarDto;
import br.com.car.rental.api.data.CarMapper;
import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.exception.RecordNotFoundException;
import br.com.car.rental.model.Car;
import br.com.car.rental.model.CarColor;
import br.com.car.rental.repository.CarRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CarService extends BaseService {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CarMapper carMapper;

	public List<CarDto> findAll() {
		return this.carRepository.findAll().stream().map(carMapper::map).toList();
	}

	public CarDto save(CarRequestDto carRequestDto) {
		Car car = this.carMapper.toModel(carRequestDto);
		return this.carMapper.map(this.carRepository.save(car));
	}

	public CarDto update(@Positive @NotNull Long id, @Valid CarRequestDto carRequestDto) {
		return this.carRepository.findById(id).map(actual -> {
			actual.setProductionYear(carRequestDto.productionYear());
			actual.setLicensePlate(carRequestDto.licensePlate());
			actual.setModel(carRequestDto.model());
			actual.setColor(CarColor.valueOf(carRequestDto.color()));
			return this.carMapper.map(this.carRepository.save(actual));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CarDto findById(@Positive @NotNull Long id) {
		return this.carMapper.map(this.findOptionalById(id).orElse(null));
	}

	public Optional<Car> findOptionalById(@Positive @NotNull Long id) {
		return this.carRepository.findById(id);
	}

	public void delete(@Positive @NotNull Long id) {
		this.carRepository.delete(this.carRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException(id)));
	}
}
