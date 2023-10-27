package br.com.car.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.car.rental.api.data.CarDto;
import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.api.data.mapper.CarMapper;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.exception.RecordNotFoundException;
import br.com.car.rental.model.Car;
import br.com.car.rental.model.CarColor;
import br.com.car.rental.model.User;
import br.com.car.rental.repository.CarRepository;
import br.com.car.rental.shared.StringUtil;
import jakarta.transaction.Transactional;
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

	public CarDto save(User user, CarRequestDto carRequestDto) {
		validateCar(-1l, carRequestDto);

		Car car = this.carMapper.toModel(carRequestDto);
		user.addCar(car);
		return this.carMapper.map(this.carRepository.save(car));
	}

	private void validateCar(Long id, CarRequestDto carRequestDto) {
		if (carRequestDto.productionYear() == null ||
				StringUtil.isNullOrEmpty(
						carRequestDto.licensePlate(), 
						carRequestDto.model(),
						carRequestDto.color())) {
			throw new BusinessException("Missing fields");
		}
		
		if (!CarColor.contains(carRequestDto.color())) {
			throw new BusinessException("Invalid fields");
		}

		carRepository.findAllByLicensePlate(id, carRequestDto.licensePlate()).stream().findAny().ifPresent(c -> {
			throw new BusinessException("License plate already exists");
		});
	}

	public CarDto update(User user, @Positive @NotNull Long id, @Valid CarRequestDto carRequestDto) {
		validateCar(id, carRequestDto);

		return this.carRepository.findByUserAndId(user.getLogin(), id).map(actual -> {
			actual.setProductionYear(carRequestDto.productionYear());
			actual.setLicensePlate(carRequestDto.licensePlate());
			actual.setModel(carRequestDto.model());
			actual.setColor(CarColor.valueOf(carRequestDto.color()));
			return this.carMapper.map(this.carRepository.save(actual));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CarDto findByUserAndId(User user, @Positive @NotNull Long id) {
		return this.carMapper.map(this.carRepository.findByUserAndId(user.getLogin(), id).orElse(null));
	}

	public Optional<Car> findOptionalById(@Positive @NotNull Long id) {
		return this.carRepository.findById(id);
	}

    @Transactional
	public void delete(User user, @Positive @NotNull Long id) {
		Car car = this.carRepository.findByUserAndId(user.getLogin(), id).orElse(null);
		if (car == null) {
			new RecordNotFoundException(id);
		}
		this.carRepository.delete(car.getId());
	}

	public List<CarDto> findAllByUser(User user) {
		return this.carRepository.findAllByUser(user.getLogin()).stream().map(carMapper::map).toList();
	}
}
