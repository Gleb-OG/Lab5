package lab.managers;

import lab.Main;
import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.utils.IDGenerator;
import lab.utils.InteractiveParser;
import java.time.LocalDate;
import java.util.*;


public class CollectionManager {

    private TreeMap<Integer, Organization> collection = new TreeMap<>();
    private final LocalDate initializationDate = LocalDate.now();

    public CollectionManager() {
    }

    public void loadCollection(TreeMap<Integer, Organization> collection) {
        this.collection = collection;
    }

    public void addOrganization(int id, Organization organization) {
        try {
            IDGenerator.registerID(id);
        } catch (IllegalArgumentException e) {
            return;
        }
        collection.put(id, organization);
    }

    public TreeMap<Integer, Organization> getCollection() {
        return collection;
    }

    public Organization getOrganizationByID(int id) {
        if (collection.containsKey(id)) {
            return collection.get(id);
        }
        if(!Main.scriptMode) System.out.println("Элемента с таким id не обнаружено");
        return null;
    }

    public void removeOrganizationByID(int id) {
        Organization organization = getOrganizationByID(id);
        if (organization != null) {
            collection.remove(id);
            IDGenerator.releaseID(id);
            System.out.println("Элемент с ID: " + id + " удалён из коллекции.");
        }
    }

    public void updateID(int id) {
        InteractiveParser parser = new InteractiveParser();
        try {
            Organization oldOrganization = getOrganizationByID(id);
            if (oldOrganization != null) {
                collection.remove(id);
                IDGenerator.releaseID(id);
                Organization newOrganization = parser.parseOrganization();
                collection.put(id, newOrganization);
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Введите значение int > 0");
        } catch (InvalidDataException ignore) {
        }

    }

    public void info() {
        System.out.println("Тип коллекции: TreeMap, \n" +
                "Дата создания: " + initializationDate + ",\n" +
                "Количество элементов: " + collection.size());
    }


//    public void removeLower(String[] args) {
//        int id = Validator.validateInt(args[0]);
//        if (!getCollection().containsKey(id)) {
//            throw new InvalidDataException("Ошибка: ключ " + id + " не существует.");
//        }
//        TreeMap<Integer, Organization> collection = getCollection();
//        Iterator<Map.Entry<Integer, Organization>> iterator = collection.entrySet().iterator();
//        int removedCount = 0;
//
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, Organization> entry = iterator.next();
//            if (entry.getKey() < id) {
//                iterator.remove();
//                removedCount++;
//            }
//        }
//        System.out.println("Удалено элементов: " + removedCount);
//    }
}