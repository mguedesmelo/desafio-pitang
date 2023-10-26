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
	
	public static boolean contains(String test) {
	    for (CarColor c : CarColor.values()) {
	        if (c.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
}