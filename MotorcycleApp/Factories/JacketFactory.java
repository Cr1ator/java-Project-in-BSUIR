package MotorcycleApp.Factories;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Models.Jacket;

public class JacketFactory extends AmmunitionFactory {
    @Override
    public Ammunition createAmmunition(double price, double weight) {
        return new Jacket(price, weight);
    }
}
