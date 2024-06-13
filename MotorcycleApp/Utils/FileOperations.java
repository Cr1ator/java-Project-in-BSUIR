package MotorcycleApp.Utils;

import MotorcycleApp.Models.Ammunition;

import java.io.*;
import java.util.List;

public class FileOperations {

    public static void writeAmmunitionToFile(List<Ammunition> ammunition, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(ammunition);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Ammunition> readAmmunitionFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Ammunition>) ois.readObject();
        }
    }
}
