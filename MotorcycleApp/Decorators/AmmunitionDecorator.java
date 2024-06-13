package MotorcycleApp.Decorators;

import MotorcycleApp.Models.Ammunition;

public abstract class AmmunitionDecorator extends Ammunition {
    protected Ammunition decoratedAmmunition;

    public AmmunitionDecorator(Ammunition decoratedAmmunition) {
        super(decoratedAmmunition.getName(), decoratedAmmunition.getPrice(), decoratedAmmunition.getWeight());
        this.decoratedAmmunition = decoratedAmmunition;
    }

    @Override
    public String getName() {
        return decoratedAmmunition.getName();
    }

    @Override
    public double getPrice() {
        return decoratedAmmunition.getPrice();
    }

    @Override
    public double getWeight() {
        return decoratedAmmunition.getWeight();
    }

    @Override
    public String toString() {
        return decoratedAmmunition.toString();
    }
}
