package MotorcycleApp.Factories;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Models.Helmet;

public class HelmetFactory extends AmmunitionFactory {
    @Override
    public Ammunition createAmmunition(double price, double weight) {
        return new Helmet(price, weight);
    }
}
