package com.design.strategy.example;

public class MallardDuck extends Duck {

    public MallardDuck() {
        this.flyBehavior = new FlyWithWings();
    }

}