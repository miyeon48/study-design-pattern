package com.design.strategy.example;

public class StrategyPartternMain {

    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        
        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyWithRocket());
        modelDuck.performFly();
    }
}
