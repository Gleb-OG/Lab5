package lab.commands;

import lab.managers.KeyManager;
import java.util.TreeMap;

/**
 * Команда, очищающая коллекцию организаций.
 */
public class Clear extends Command {

    public Clear() {
        super("clear", "Очищение коллекции", 0);
    }

    @Override
    public void execute() {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.println("Коллекция итак пустая.");
        } else {
            collectionManager.loadCollection(new TreeMap<>());
            KeyManager.clearAllKeys();
            System.out.println("Коллекция очищена.");
        }
    }
}
