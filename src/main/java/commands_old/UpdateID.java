package commands_old;

import interfaces.Command;
import exceptions.InvalidDataException;

// Проверить, работает ли вызов другой команды/удаляется ли ключ вместе со значением после .remove(key)
public class UpdateID implements Command {
    private String key;

    //починить недоэксепшены
    public UpdateID(String[] args) {
        if (args.length < 1) {
            System.out.println("Ключ не указан: update <key>.");
        } else if (args.length > 1) {
            System.out.println("Команда может принимать только один аргумент: update <key>");
        }
        this.key = args[0];
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (!collectionManager.getCollection().containsKey(key)) {
            throw new InvalidDataException("Ошибка: элемента с ключом " + key + " не существует.");
        }
        try {
            String[] newID = new String[]{this.key};
            RemoveKey removeCommand = new RemoveKey(newID);
            InsertElement insertCommand = new InsertElement(newID);
            removeCommand.execute(collectionManager);
            insertCommand.execute(collectionManager);

            System.out.println("Элемент по ключу " + key + " обновлен успешно.");
        } catch (InvalidDataException e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}

