package MotorcycleApp.Factories;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Models.Boots;

public class BootsFactory extends AmmunitionFactory {
    @Override
    public Ammunition createAmmunition(double price, double weight) {
        return new Boots(price, weight);
    }
}
