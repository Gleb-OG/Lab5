package commands;

import data.Address;
import data.Coordinates;
import data.Organization;
import data.OrganizationType;
import exceptions.InvalidDataException;
import interfaces.Command;
import managers.CollectionManager;
import utils.InteractiveParser;
import utils.Validator;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class RemoveLower implements Command {
    private String key;

    public RemoveLower(String[] args) {
        if (args.length < 1) {
            throw new InvalidDataException("Ключ не указан: remove_lower_key <key>");
        } else if (args.length > 1) {
            throw new InvalidDataException("Команда может принимать только один аргумент: remove_lower_key <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(CollectionManager collectionManager, String[] args) {
        try {
            int id = Validator.validateInt(this.key);
            if (!collectionManager.getCollection().containsKey(id)) {
                throw new InvalidDataException("Ошибка: ключ " + id + " не существует.");
            }
            TreeMap<Integer, Organization> collection = collectionManager.getCollection();
            Iterator<Map.Entry<Integer, Organization>> iterator = collection.entrySet().iterator();
            int removedCount = 0;

            while (iterator.hasNext()) {
                Map.Entry<Integer, Organization> entry = iterator.next();
                if (entry.getKey() < id) {
                    iterator.remove();
                    removedCount++;
                }
            }
            System.out.println("Удалено элементов: " + removedCount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new InvalidDataException(e.getMessage());
        }
    }
}