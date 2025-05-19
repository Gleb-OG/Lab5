package managers;

import data.Organization;
import exceptions.InvalidDataException;
import utils.CSVProcessor;
import utils.IDGenerator;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

//Добавить сортировку и, возможно, переопределить методы put, remove, replace

public class CollectionManager {
    private TreeMap<Integer, Organization> collection = new TreeMap<>();
    private LocalDate initializationDate;

    public CollectionManager(String filename) {
        this.initializationDate = LocalDate.now();
        loadCollection(filename);
    }

    public void loadCollection(String filename) {
        try {
            List<Organization> organizations = CSVProcessor.loadFromCSV(filename);
            organizations.forEach(org -> {
                if (collection.containsKey(org.getID())) {
                    throw new InvalidDataException("Этот ID уже существует: " + org.getID());
                }
                collection.put(org.getID(), org);
            });
            int lastID = organizations.stream().mapToInt(org -> org.getID()).max().orElse(0);
            IDGenerator.init(lastID);
        } catch(Exception e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }

    public TreeMap<Integer, Organization> getCollection() {
        return collection;
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }
}