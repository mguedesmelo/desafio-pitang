package br.com.car.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.car.rental.model.Car;
import br.com.car.rental.repository.CarRepository;

@Service
public class CarService extends BaseService {
	@Autowired
	private CarRepository carRepository;

	public List<Car> findAll() {
		return this.carRepository.findAll();
	}

	public Car save(Car user) {
		return this.carRepository.save(user);
	}

	public Car findById(Long id) {
		return this.findOptionalById(id).orElse(null);
	}

	public Optional<Car> findOptionalById(Long id) {
		return this.carRepository.findById(id);
	}

	public void remove(Car user) {
		this.carRepository.delete(user);
	}

	public Car findImageById(Long id) {
		return this.carRepository.findImageById(id).orElse(null);
	}
}
