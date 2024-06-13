package MotorcycleApp.Decorators;

import MotorcycleApp.Models.Ammunition;

public class DiscountedAmmunition extends AmmunitionDecorator {
    private double discountPercentage;

    public DiscountedAmmunition(Ammunition decoratedAmmunition, double discountPercentage) {
        super(decoratedAmmunition);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        return decoratedAmmunition.getPrice() * (1 - discountPercentage / 100);
    }

    @Override
    public String toString() {
        return decoratedAmmunition.toString() + " (со скидкой " + discountPercentage + "%)";
    }
}
