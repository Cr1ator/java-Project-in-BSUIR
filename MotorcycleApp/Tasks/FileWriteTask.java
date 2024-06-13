package MotorcycleApp.Tasks;

import MotorcycleApp.Models.Ammunition;
import MotorcycleApp.Utils.FileOperations;

import java.util.List;
import java.util.concurrent.Callable;

public class FileWriteTask implements Callable<Void> {
    private final List<Ammunition> ammunition;
    private final String filename;

    public FileWriteTask(List<Ammunition> ammunition, String filename) {
        this.ammunition = ammunition;
        this.filename = filename;
    }

    @Override
    public Void call() throws Exception {
        FileOperations.writeAmmunitionToFile(ammunition, filename);
        return null;
    }
}
