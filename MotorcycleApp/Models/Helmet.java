package MotorcycleApp.Models;

public class Helmet extends Ammunition {
    private static final long serialVersionUID = 1L;

    public Helmet(double price, double weight) {
        super("Шлем", price, weight);
    }
}
