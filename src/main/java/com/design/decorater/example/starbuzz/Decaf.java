package com.design.decorater.example.starbuzz;

public class Decaf extends Beverage {
	public Decaf() {
		description = "디카페인";
	}
 
	public double cost() {
		return 1.05;
	}
}

