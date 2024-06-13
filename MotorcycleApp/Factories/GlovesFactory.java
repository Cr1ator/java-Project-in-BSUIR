package MotorcycleApp.Factories;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Models.Gloves;

public class GlovesFactory extends AmmunitionFactory {
    @Override
    public Ammunition createAmmunition(double price, double weight) {
        return new Gloves(price, weight);
    }
}
