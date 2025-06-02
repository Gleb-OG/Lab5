package lab.commands_old;

import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.interfaces.CommandInterface;
import lab.managers.CollectionManager;
import lab.utils.Validator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static org.apache.commons.collections4.MultiMapUtils.getCollection;

public class RemoveLower implements CommandInterface {
    private String key;
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute(String[] args) {
        collectionManager.removeLower(args);
    }

    public String description() {
        System.out.println("replace_if_lowe <key> {element}: Заменить значение по ключу, если новое значение меньше старого");
    }
}
    public void removeLower(String[] args) throws InvalidDataException {
        int id = Validator.validateInt(args[0]);
        if (!getCollection().containsKey(id)) {
            throw new InvalidDataException("Ошибка: ключ " + id + " не существует.");
        }
        TreeMap<Integer, Organization> collection = getCollection();
        Iterator<Map.Entry<Integer, Organization>> iterator = collection.entrySet().iterator();
        int removedCount = 0;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Organization> entry = iterator.next();
            if (entry.getKey() < id) {
                iterator.remove();
                removedCount++;
            }
        }

//    public RemoveLower(String[] args) {
//        if (args.length < 1) {
//            throw new InvalidDataException("Ключ не указан: remove_lower_key <key>");
//        } else if (args.length > 1) {
//            throw new InvalidDataException("Команда может принимать только один аргумент: remove_lower_key <key>");
//        }
//        this.key = args[0];
//    }