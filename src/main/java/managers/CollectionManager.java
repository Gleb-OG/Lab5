package managers;

import data.Organization;
import exceptions.InvalidDataException;
import utils.CSVProcessor;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;


public class CollectionManager {
    private TreeMap<Integer, Organization> collection = new TreeMap<>();
    private final LocalDate initializationDate;

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
        } catch(Exception e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }

    public void addOrganization(int key, Organization organization) {
        collection.put(key, organization);
    }

    public TreeMap<Integer, Organization> getCollection() {
        return collection;
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }
}