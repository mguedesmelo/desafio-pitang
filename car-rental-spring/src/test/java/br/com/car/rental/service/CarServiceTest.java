package br.com.car.rental.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.car.rental.api.data.mapper.CarMapper;
import br.com.car.rental.model.Car;
import br.com.car.rental.model.CarColor;
import br.com.car.rental.model.User;
import br.com.car.rental.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
	@InjectMocks
	private CarService carService;
	@Mock
	private CarRepository carRepository;
	@Mock
	private CarMapper carMapper;
	
	List<Car> carsHeisenberg;
	List<Car> carsJessePinkman;
	User userHeisenberg;
	User userJessePinkman;
	
	@BeforeEach
	public void setUp() {
		userHeisenberg = new User(
				"Walter", 
				"White", 
				"heisenberg@somedomain.com", 
				LocalDate.of(1958, 9, 7), 
				"heisenberg", 
				"$2a$10$SDelfowfCRWei0rkUI5IIO1dLNKYrcHP4cjbjoJLViYj4h/0a7VdO",
				"+1 515-516-0624");
		
		carsHeisenberg = Arrays.asList(
				new Car(2005, "XYZ 5678", "Pontiac Aztek", CarColor.White, userHeisenberg),
				new Car(2012, "JKL 1234", "Chrysler 300C SRT-8", CarColor.Black, userHeisenberg),
				new Car(2012, "QRS 9893", "Dodge Challenger SRT-8", CarColor.Red, userHeisenberg),
				new Car(1992, "GNM 4820", "Buick LeSabre", CarColor.Blue, userHeisenberg));

		userJessePinkman = new User(
				"Jesse", 
				"Pinkman", 
				"pinkman@somedomain.com", 
				LocalDate.of(1984, 10, 22), 
				"pinkman", 
				"$2a$10$SDelfowfCRWei0rkUI5IIO1dLNKYrcHP4cjbjoJLViYj4h/0a7VdO", 
				"+1 707-719-0993");

		carsJessePinkman = Arrays.asList(
				new Car(1986, "HLO 2056", "Fleetwood Bounder", CarColor.White, userJessePinkman),
				new Car(1982, "RND 6893", "Chevrolet Monte Carlo", CarColor.WineRed, userJessePinkman),
				new Car(1986, "PQT 3570", "Toyota Tercel", CarColor.White, userJessePinkman),
				new Car(1978, "LPM 1201", "Chevrolet El Camino", CarColor.Red, userJessePinkman));
	}

	/*
	 public CarDto findByUserAndId(User user, @Positive @NotNull Long id)

	 public CarDto save(User user, CarRequestDto carRequestDto);

	 public CarDto update(User user, @Positive @NotNull Long id, @Valid CarRequestDto carRequestDto)

	 public Optional<Car> findOptionalById(@Positive @NotNull Long id)

	 public void delete(User user, @Positive @NotNull Long id)
	 */

	@Test
	public void findByUserAndIdSuccess() {
//		when(this.carService.findByUserAndId(userHeisenberg, 1l)).thenReturn(Collections.singletonList(userJessePinkman));
	}
}
