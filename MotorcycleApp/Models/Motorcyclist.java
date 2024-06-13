package MotorcycleApp.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Motorcyclist implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Ammunition> equipment = new ArrayList<>();

    public void addAmmunition(Ammunition item) {
        if (item == null) {
            throw new IllegalArgumentException("Item не может быть null.");
        }
        equipment.add(item);
    }

    public double getTotalCost() {
        return equipment.stream().mapToDouble(Ammunition::getPrice).sum();
    }

    public void sortAmmunitionByWeight() {
        equipment.sort((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()));
    }

    public void sortAmmunitionByPrice() {
        equipment.sort((a1, a2) -> Double.compare(a1.getPrice(), a2.getPrice()));
    }

    public List<Ammunition> findAmmunitionByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }
        return equipment.stream().filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    public Map<String, List<Ammunition>> groupAmmunitionByType() {
        return equipment.stream().collect(Collectors.groupingBy(Ammunition::getName));
    }

    public List<Ammunition> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Ammunition> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return equipment.stream().map(Ammunition::toString).collect(Collectors.joining("\n"));
    }
}
