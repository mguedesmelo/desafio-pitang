package br.com.car.rental.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.car.rental.api.data.CarDto;
import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.exception.BusinessException;
import br.com.car.rental.model.Car;
import br.com.car.rental.model.User;
import br.com.car.rental.service.CarService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/cars")
public class CarRestController extends BaseRestController<User> {
	@Autowired
	private CarService carService;

	@GetMapping
	public List<CarDto> findAll() {
		return this.carService.findAllByUser(getLoggedUser());
	}
	
	@PostMapping
	public ResponseEntity<CarDto> save(@RequestBody @Valid CarRequestDto carRequestDto) {
		CarDto savedCar = this.carService.save(this.getLoggedUser(), carRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
	}

	@GetMapping(value = "/{id}")
	public CarDto findById(@PathVariable("id") @Positive @NotNull Long id) {
		return this.carService.findByUserAndId(getLoggedUser(), id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @Positive @NotNull Long id) {
		this.carService.delete(getLoggedUser(), id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarDto> update(@PathVariable @Positive @NotNull Long id,
			@RequestBody @Valid CarRequestDto car) {
		CarDto savedCar = this.carService.update(getLoggedUser(), id, car);
		return ResponseEntity.status(HttpStatus.OK).body(savedCar);
	}

	@PostMapping("/upload/{id}")
    public ResponseEntity<CarDto> upload(@PathVariable @Positive @NotNull Long id, 
    		@RequestParam("imagem") MultipartFile file) {
        try {
        	return ResponseEntity.status(HttpStatus.OK).body(
        			this.carService.saveImage(id, file.getName(), file.getSize(), 
        			file.getContentType(), file.getBytes()));
        } catch (IOException e) {
            throw new BusinessException("Error saving image");
        }
    }

	@GetMapping("/image/{id}")
    public void image(@PathVariable Long id, HttpServletResponse response) {
    	Optional<Car> optional = this.carService.findImagemById(id);
		optional.ifPresentOrElse((c) -> {
			loadImage(response, c.getImageType(), c.getImage());
		}, () -> {
			loadNoPhoto(response);
		});
    }
}
