package com.design.decorater.example;

public abstract class CondimentDecorator extends Beverage {
	public Beverage beverage; //감쌀 객체 지정
	public abstract String getDescription();
	
	public Size getSize() {
		return beverage.getSize();
	}
}
