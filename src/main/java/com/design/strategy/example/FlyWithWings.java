package com.design.strategy.example;

public class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        // 날 수 있음
        System.out.println("fly");
    }
}