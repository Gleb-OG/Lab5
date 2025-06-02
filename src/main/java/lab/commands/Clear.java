package lab.commands;

import lab.data.Organization;

import java.util.HashSet;
import java.util.TreeMap;

public class Clear extends Command {

    public Clear() {
        super("clear", "Очищение коллекции", 0);
    }

    @Override
    public void execute() {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция итак пустая.");
        } else {
            collectionManager.loadCollection(new TreeMap<Integer, Organization>());
            System.out.println("Коллекция очищена.");
        }
    }
}
