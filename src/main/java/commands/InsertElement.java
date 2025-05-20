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


public class InsertElement implements Command {
    private String key;

    public InsertElement(String[] args) {
        if (args.length < 1) {
            throw new InvalidDataException("Ключ не указан: insert <key>");
        } else if (args.length > 1) {
            throw new InvalidDataException("Команда может принимать только один аргумент: insert <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(CollectionManager collectionManager, String[] args) {
        try {
            int key = Validator.validateInt(this.key);
            if (collectionManager.getCollection().containsKey(key)) {
                throw new InvalidDataException("Ошибка: ключ " + key + " уже существует.");
            }
            System.out.println("Введите данные организации:");
            Organization organization = InteractiveParser.parseOrganization();
            collectionManager.addOrganization(key, organization);

            System.out.println("Элемент загружен в коллекцию по ключу " + key + " успешно.");
        } catch (InvalidDataException e) {
            throw new InvalidDataException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
