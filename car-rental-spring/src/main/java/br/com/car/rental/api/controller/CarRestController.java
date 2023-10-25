package br.com.car.rental.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rental.api.data.CarDto;
import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.model.User;
import br.com.car.rental.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/cars")
public class CarRestController extends BaseRestController<User> {
	@Autowired
	private CarService carService;

	@PostMapping
	public ResponseEntity<CarDto> save(@RequestBody @Valid CarRequestDto car) {
		CarDto savedCar = this.carService.save(car);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CarDto> update(@PathVariable @Positive @NotNull Long id,
			@RequestBody @Valid CarRequestDto car) {
		CarDto savedCar = this.carService.update(id, car);
		return ResponseEntity.status(HttpStatus.OK).body(savedCar);
	}

	@GetMapping
	public List<CarDto> findAll() {
		return this.carService.findAll();
	}

	@GetMapping(value = "/{id}")
	public CarDto findById(@PathVariable("id") @Positive @NotNull Long id) {
		return this.carService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @Positive @NotNull Long id) {
		this.carService.delete(id);
	}
}
