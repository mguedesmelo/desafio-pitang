package br.com.car.rental;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.car.rental.api.data.CarRequestDto;
import br.com.car.rental.api.data.UserRequestDto;
import br.com.car.rental.model.User;
import br.com.car.rental.service.UserService;

@SpringBootApplication
public class CarRentalSpringApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(CarRentalSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CarRequestDto> carsHeisenberg = Arrays.asList(
				new CarRequestDto(2005, "XYZ 5678", "Pontiac Aztek", "White"),
				new CarRequestDto(2012, "JKL 1234", "Chrysler 300C SRT-8", "Black"),
				new CarRequestDto(2012, "QRS 9893", "Dodge Challenger SRT-8", "Red"),
				new CarRequestDto(1992, "GNM 4820", "Buick LeSabre", "Blue"));

		List<CarRequestDto> carsJessePinkman = Arrays.asList(
				new CarRequestDto(1986, "HLO 2056", "Fleetwood Bounder", "White"),
				new CarRequestDto(1982, "RND 6893", "Chevrolet Monte Carlo", "WineRed"),
				new CarRequestDto(1986, "PQT 3570", "Toyota Tercel", "White"),
				new CarRequestDto(1978, "LPM 1201", "Chevrolet El Camino", "Red"));

		UserRequestDto userHeisenberg = new UserRequestDto("Walter", "White", "heisenberg@somedomain.com", 
				LocalDate.of(1958, 9, 7), "heisenberg",	"+1 515-516-0624","h3ll0", carsHeisenberg);

		UserRequestDto userJessePinkman = new UserRequestDto("Jesse", "Pinkman", "pinkman@somedomain.com", 
				LocalDate.of(1984, 10, 22), "pinkman", "+1 707-719-0993", "h3ll0", carsJessePinkman);

		this.userService.save(userHeisenberg);
		this.userService.save(userJessePinkman);

		List<User> users = this.userService.findAll();
		users.stream().forEach(System.out::println);
	}
	/**
	- Mapeando uma classe para um dto
	@GetMapping("/dto")
	public List<EmployeeDto> getEmployeesDto() {
	    log.info("In controller");
	    return this.employeeService.getEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	private EmployeeDto convertToDto(Employee employee) {
	    return this.modelMapper.map(employee, EmployeeDto.class);
	}
	
	
	*/

}
