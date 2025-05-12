package managers;

import com.sun.source.tree.Tree;
import data.Organization;
import utils.CSVProcessor;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.TreeMap;

public class CollectionManager {
    private static TreeMap<Integer, Organization> collection = new TreeMap<>();
    private static LocalDate initializationDate = LocalDate.now();

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