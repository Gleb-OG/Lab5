package commands_old;

import interfaces.Command;
import exceptions.InvalidDataException;

/**
 * Команда для очистки коллекции.
 */

public class Clear implements Command {
    /**
     * Очищает коллекцию организаций.
     *
     * @param args
     */

    @Override
    public void execute(String[] args) throws InvalidDataException {
        if (args.length != 0) {
            throw new InvalidDataException("Некорректный ввод команды: команда 'clear' не принимает аргументов.");
        }
        collectionManager.getCollection().clear();
        System.out.println("Коллекция очищена.");
    }
}
