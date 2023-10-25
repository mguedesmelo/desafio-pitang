package br.com.car.rental.model;

import lombok.Getter;

public enum CarColor {
	Red("Red"), 
	Yellow("Yellow"), 
	Blue("Blue"),
	White("White"), 
	WineRed("WineRed"), 
	Black("Black");

	@Getter
	private String color;

	CarColor(String color) {
		this.color = color;
	}
}