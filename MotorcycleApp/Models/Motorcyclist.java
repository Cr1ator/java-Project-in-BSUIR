package MotorcycleApp.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Motorcyclist {
    // Использование коллекции ArrayList для хранения данных
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

    // Сортировка данных по весу с использованием лямбда-выражения
    public void sortAmmunitionByWeight() {
        equipment.sort((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()));
    }

    // Сортировка данных по цене с использованием лямбда-выражения
    public void sortAmmunitionByPrice() {
        equipment.sort((a1, a2) -> Double.compare(a1.getPrice(), a2.getPrice()));
    }

    // Фильтрация данных по диапазону цены с использованием stream API
    public List<Ammunition> findAmmunitionByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }
        return equipment.stream()
            .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
            .collect(Collectors.toList());
    }

    // Группировка данных по типу с использованием stream API
    public Map<String, List<Ammunition>> groupAmmunitionByType() {
        return equipment.stream().collect(Collectors.groupingBy(Ammunition::getName));
    }

    @Override
    public String toString() {
        return equipment.stream().map(Ammunition::toString).collect(Collectors.joining("\n"));
    }
}