package com.design.decorater.example.starbuzz;

public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "다크로스트";
	}
 
	public double cost() {
		return .99;
	}
}

