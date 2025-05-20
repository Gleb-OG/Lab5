package commands;

import exceptions.InvalidDataException;
import interfaces.Command;
import managers.CollectionManager;
import utils.Validator;

// Проверить, работает ли вызов другой команды/удаляется ли ключ вместе со значением после .remove(key)
public class UpdateID implements Command {
    private String key;

    //починить недоэксепшены
    public UpdateID(String[] args) {
        if (args.length < 1) {
            throw new InvalidDataException("Ключ не указан: update <key>.");
        } else if (args.length > 1) {
            throw new InvalidDataException("Команда может принимать только один аргумент: update <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(CollectionManager collectionManager, String[] args) throws Exception {
        if (!collectionManager.getCollection().containsKey(key)) {
            throw new InvalidDataException("Ошибка: элемента с ключом " + key + " не существует.");
        }
        try {
            String[] newID = new String[]{this.key};
            RemoveKey removeCommand = new RemoveKey(newID);
            InsertElement insertCommand = new InsertElement(newID);
            removeCommand.execute(collectionManager, args);
            insertCommand.execute(collectionManager, args);

            System.out.println("Элемент по ключу " + key + " обновлен успешно.");
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Ошибка ввода данных: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}

