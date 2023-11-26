package com.design.strategy.example;

public class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("no way");
    }
}
