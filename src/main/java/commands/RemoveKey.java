package commands;

import exceptions.InvalidDataException;
import interfaces.Command;
import managers.CollectionManager;
import utils.Validator;

public class RemoveKey implements Command {
    private String key;

    //починить недоэксепшены
    public RemoveKey(String[] args) {
        if (args.length < 1) {
            throw new InvalidDataException("Ключ не указан: remove_key <key>.");
        } else if (args.length > 1) {
            throw new InvalidDataException("Команда может принимать только один аргумент: remove_key <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(CollectionManager collectionManager, String[] args) throws Exception {
        try {
            int id = Validator.validateInt(this.key);
            if (!collectionManager.getCollection().containsKey(id)) {
                throw new InvalidDataException("Ошибка: ключ " + id + " не существует.");
            }
            collectionManager.getCollection().remove(id);

            System.out.println("Элемент по ключу " + key + " обновлен успешно.");
        } catch (InvalidDataException e) {
            throw new InvalidDataException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
