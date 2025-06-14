package lab.commands;

import lab.data.Organization;
import java.util.TreeMap;

/**
 * Команда, выводящая информацию о каждой организации в коллекции.
 */
public class Show extends Command {

    public Show() {
        super("show", "Вывод всех элементов коллекции", 0);
    }

    @Override
    public void execute() {
        TreeMap<Integer, Organization> organizations = collectionManager.getCollection();
        for (Integer key : organizations.keySet()) {
            System.out.println("---------Organization---------" +
                    "\nkey = " + key +
                    "\n" + organizations.get(key));
        }
        System.out.println("Количество элементов коллекции: " + organizations.size());
    }
}
