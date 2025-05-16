package managers;

import data.Organization;
import utils.CSVProcessor;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.TreeMap;

//Добавить сортировку и, возможно, переопределить методы put, remove, replace

public class CollectionManager {
    private final static TreeMap<Integer, Organization> collection = new TreeMap<>();
    private final static LocalDate initializationDate = LocalDate.now();

    public static void loadFromFile(String filename) {
        try {
            CSVProcessor.loadFromCSV(filename);
        } catch(FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }

    public static TreeMap<Integer, Organization> getCollection() {
        return collection;
    }

    public static LocalDate getInitializationDate() {
        return initializationDate;
    }
}