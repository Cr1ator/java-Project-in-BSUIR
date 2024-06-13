package MotorcycleApp;

import MotorcycleApp.Decorators.DiscountedAmmunition;
import MotorcycleApp.Factories.*;
import MotorcycleApp.Models.*;
import MotorcycleApp.Tasks.FileReadTask;
import MotorcycleApp.Tasks.FileWriteTask;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Motorcyclist motorcyclist = new Motorcyclist();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить аммуницию");
            System.out.println("2. Показать общую стоимость");
            System.out.println("3. Отсортировать аммуниции по весу");
            System.out.println("4. Отсортировать аммуниции по цене");
            System.out.println("5. Найти аммуницию по диапазону стоимости");
            System.out.println("6. Группировать аммуницию по типу");
            System.out.println("7. Показать весь инвентарь");
            System.out.println("8. Записать аммуницию в файл");
            System.out.println("9. Прочитать аммуницию из файла");
            System.out.println("10. Добавить скидку на аммуницию");
            System.out.println("11. Выйти");
            System.out.print("Сделайте выбор: ");

            try {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        addAmmunition(motorcyclist, scanner);
                        break;
                    case "2":
                        System.out.println("Общая стоимость: " + motorcyclist.getTotalCost());
                        break;
                    case "3":
                        motorcyclist.sortAmmunitionByWeight();
                        System.out.println("Аммуниция отсортированная по весу");
                        break;
                    case "4":
                        motorcyclist.sortAmmunitionByPrice();
                        System.out.println("Аммуниция отсортированная по цене");
                        break;
                    case "5":
                        findAmmunitionByPriceRange(motorcyclist, scanner);
                        break;
                    case "6":
                        groupAmmunitionByType(motorcyclist);
                        break;
                    case "7":
                        System.out.println("Весь инвентарь:");
                        System.out.println(motorcyclist);
                        break;
                    case "8":
                        writeAmmunitionToFile(motorcyclist, executorService);
                        break;
                    case "9":
                        readAmmunitionFromFile(motorcyclist, executorService);
                        break;
                    case "10":
                        addDiscountToAmmunition(motorcyclist, scanner);
                        break;
                    case "11":
                        exit = true;
                        executorService.shutdown();
                        break;
                    default:
                        System.out.println("Неправильный выбор. Попробуйте еще раз.");
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
            }
        }
        scanner.close();
    }

    private static void addAmmunition(Motorcyclist motorcyclist, Scanner scanner) {
        try {
            System.out.println("Выберите тип аммуниции:");
            System.out.println("1. Шлем");
            System.out.println("2. Жилетка");
            System.out.println("3. Перчатки");
            System.out.println("4. Ботинки");
            System.out.print("Тип: ");
            String type = scanner.nextLine();
            System.out.print("Цена: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Вес: ");
            double weight = Double.parseDouble(scanner.nextLine());

            AmmunitionFactory factory;
            switch (type) {
                case "1":
                    factory = new HelmetFactory();
                    break;
                case "2":
                    factory = new JacketFactory();
                    break;
                case "3":
                    factory = new GlovesFactory();
                    break;
                case "4":
                    factory = new BootsFactory();
                    break;
                default:
                    System.out.println("Invalid type.");
                    return;
            }

            Ammunition ammunition = factory.createAmmunition(price, weight);
            motorcyclist.addAmmunition(ammunition);

            System.out.println("Аммуниция успешно добавлена.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Price and Weight must be numeric values.");
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void findAmmunitionByPriceRange(Motorcyclist motorcyclist, Scanner scanner) {
        try {
            System.out.print("Мин цена: ");
            double minPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Макс цена: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Ammunition> foundItems = motorcyclist.findAmmunitionByPriceRange(minPrice, maxPrice);
            System.out.println("Найденная аммуниция:");
            for (Ammunition item : foundItems) {
                System.out.println(item);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Min Price and Max Price must be numeric values.");
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }

    private static void groupAmmunitionByType(Motorcyclist motorcyclist) {
        Map<String, List<Ammunition>> groupedItems = motorcyclist.groupAmmunitionByType();
        System.out.println("Аммуниция, сгруппированная по типу:");
        groupedItems.forEach((type, items) -> {
            System.out.println("Тип: " + type);
            items.forEach(item -> System.out.println("  " + item));
        });
    }

    private static void writeAmmunitionToFile(Motorcyclist motorcyclist, ExecutorService executorService) {
        try {
            Callable<Void> writeTask = new FileWriteTask(motorcyclist.getEquipment(), "ammunition.json");
            Future<Void> future = executorService.submit(writeTask);
            future.get();
            System.out.println("Данные успешно записаны в файл.");
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readAmmunitionFromFile(Motorcyclist motorcyclist, ExecutorService executorService) {
        try {
            Callable<List<Ammunition>> readTask = new FileReadTask("ammunition.json");
            Future<List<Ammunition>> future = executorService.submit(readTask);
            List<Ammunition> ammunition = future.get();
            motorcyclist.setEquipment(ammunition);
            System.out.println("Данные успешно прочитаны из файла:");
            ammunition.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void addDiscountToAmmunition(Motorcyclist motorcyclist, Scanner scanner) {
        try {
            System.out.print("Введите процент скидки: ");
            double discount = Double.parseDouble(scanner.nextLine());

            List<Ammunition> discountedAmmunition = motorcyclist.getEquipment().stream()
                    .map(ammunition -> new DiscountedAmmunition(ammunition, discount))
                    .collect(Collectors.toList());

            motorcyclist.setEquipment(discountedAmmunition);
            System.out.println("Скидка успешно добавлена.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Discount must be a numeric value.");
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
        }
    }
}
