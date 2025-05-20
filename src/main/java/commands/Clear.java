package commands;

import exceptions.InvalidDataException;
import interfaces.Command;
import managers.CollectionManager;

/**
 * Команда для очистки коллекции.
 */

public class Clear implements Command {
    /**
     * Очищает коллекцию организаций.
     * @param collectionManager Менеджер коллекции, из которого мы получаем доступ к самой коллекции организаций.
     * @param args Аргументы команды (не используются).
     */

    @Override
    public void execute(CollectionManager collectionManager, String[] args) throws InvalidDataException {
        if (args.length != 0) {
            throw new InvalidDataException("Некорректный ввод команды: команда 'clear' не принимает аргументов.");
        }
        collectionManager.getCollection().clear();
        System.out.println("Коллекция очищена.");
    }
}
