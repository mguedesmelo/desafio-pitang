package br.com.car.rental.model;

import lombok.Getter;

public enum CarColor {
    Red("Red", "bg-primary"), 
    Yellow("Yellow", "bg-secondary"), 
    Blue("Blue", "bg-warning"), 
    White("White", "bg-success"), 
    WineRed("WineRed", "bg-success"), 
    Black("Black", "bg-danger");
    
	@Getter
    private String color;

	@Getter
    private String cssClass;

	CarColor(String color, String cssClass) {
        this.color = color;
        this.cssClass = cssClass;
    }
}