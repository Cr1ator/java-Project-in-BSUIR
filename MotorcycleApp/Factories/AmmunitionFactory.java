package MotorcycleApp.Factories;

import MotorcycleApp.Models.Ammunition;

public abstract class AmmunitionFactory {
    public abstract Ammunition createAmmunition(double price, double weight);
}
