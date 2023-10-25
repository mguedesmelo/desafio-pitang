package br.com.car.rental.api.data;

public record CarDto(Long id, Integer productionYear, String licensePlate, String model, 
		String color) {

}
