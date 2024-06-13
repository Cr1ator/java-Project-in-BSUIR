package MotorcycleApp.Models;

import MotorcycleApp.NegativeValueException;

public abstract class Ammunition {
    private String name;
    private double price;
    private double weight;

    public Ammunition(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public void setPrice(double price) throws NegativeValueException {
        if (price < 0) {
            throw new NegativeValueException("Цена не может быть ниже 0.");
        }
        this.price = price;
    }

    public void setWeight(double weight) throws NegativeValueException {
        if (weight < 0) {
            throw new NegativeValueException("Вес не может быть ниже 0.");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + ": Цена = " + price + ", Вес = " + weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;

        Ammunition other = (Ammunition) obj;
        return name.equals(other.name) && price == other.price && weight == other.weight;
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(name, price, weight);
    // }
}