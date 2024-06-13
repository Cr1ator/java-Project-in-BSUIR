package MotorcycleApp.Tasks;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Utils.FileOperations;

import java.util.List;
import java.util.concurrent.Callable;

public class FileReadTask implements Callable<List<Ammunition>> {
    private final String filename;

    public FileReadTask(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Ammunition> call() throws Exception {
        return FileOperations.readAmmunitionFromFile(filename);
    }
}
