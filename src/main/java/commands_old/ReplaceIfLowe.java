package commands_old;

import interfaces.Command;
import data.Organization;
import exceptions.InvalidDataException;
import utils.InteractiveParser;
import utils.Validator;

import java.util.TreeMap;


public class ReplaceIfLowe implements Command {
    private final String key;

    public ReplaceIfLowe(String[] args) throws InvalidDataException {
        if (args.length < 1) {
            throw new InvalidDataException("Ключ не указан: remove_lower_key <key>");
        } else if (args.length > 1) {
            throw new InvalidDataException("Команда может принимать только один аргумент: remove_lower_key <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(String[] args) {
        try {
            int id = Validator.validateInt(this.key);
            if (!collectionManager.getCollection().containsKey(id)) {
                throw new InvalidDataException("Ошибка: ключ " + id + " не существует.");
            }
            TreeMap<Integer, Organization> collection = collectionManager.getCollection();

            System.out.println("Введите данные нового элемента:");
            Organization newOrg = InteractiveParser.parseOrganization();

            Organization oldOrg = collection.get(id);
            if (newOrg.compareTo(oldOrg) < 0) {
                collection.put(id, newOrg);
                System.out.println("Элемент с ключом " + id + " успешно заменен.");
            } else {
                System.out.println("Новый элемент не меньше старого. Замена не выполнена.");
            }
        } catch (InvalidDataException e) {
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}