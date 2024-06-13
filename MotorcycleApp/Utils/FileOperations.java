package MotorcycleApp.Utils;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Models.Helmet;
import MotorcycleApp.Models.Jacket;
import MotorcycleApp.Models.Gloves;
import MotorcycleApp.Models.Boots;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperations {

    public static void writeAmmunitionToFile(List<Ammunition> ammunition, String filename) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")) {
            String json = toJson(ammunition);
            writer.write(json);
        }
    }

    public static List<Ammunition> readAmmunitionFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String json = reader.lines().collect(Collectors.joining());
            return fromJson(json);
        }
    }

    private static String toJson(List<Ammunition> ammunition) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < ammunition.size(); i++) {
            Ammunition item = ammunition.get(i);
            sb.append("{");
            sb.append("\"name\":\"").append(item.getName()).append("\",");
            sb.append("\"price\":").append(item.getPrice()).append(",");
            sb.append("\"weight\":").append(item.getWeight());
            sb.append("}");
            if (i < ammunition.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static List<Ammunition> fromJson(String json) {
        json = json.substring(1, json.length() - 1);  // remove []
        String[] items = json.split("},\\{");

        List<Ammunition> ammunitionList = new ArrayList<>();
        for (String item : items) {
            item = item.replace("{", "").replace("}", "");
            String[] attributes = item.split(",");
            String name = attributes[0].split(":")[1].replace("\"", "");
            double price = Double.parseDouble(attributes[1].split(":")[1]);
            double weight = Double.parseDouble(attributes[2].split(":")[1]);

            switch (name) {
                case "Шлем":
                    ammunitionList.add(new Helmet(price, weight));
                    break;
                case "Жилетка":
                    ammunitionList.add(new Jacket(price, weight));
                    break;
                case "Перчатки":
                    ammunitionList.add(new Gloves(price, weight));
                    break;
                case "Ботинки":
                    ammunitionList.add(new Boots(price, weight));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown ammunition type: " + name);
            }
        }

        return ammunitionList;
    }
}
