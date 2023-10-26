package br.com.car.rental.api.data;

public record CarDto(Long id, Integer year, String licensePlate, 
		String model, String color) {

}
