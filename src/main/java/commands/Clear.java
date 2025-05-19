package commands;

import data.Organization;
import exceptions.InvalidDataException;
import interfaces.Command;
import java.util.TreeMap;


/**
 * Команда для очистки коллекции.
 */
public class Clear implements Command {
    /**
     * Очищает коллекцию организаций.
     * @param collection Коллекция организаций.
     * @param args Аргументы команды (не используются).
     */

    @Override
    public void execute(TreeMap<Integer, Organization> collection, String[] args) throws InvalidDataException {
        if (args.length != 0) {
            throw new InvalidDataException("Некорректный ввод команды: команда 'clear' не принимает аргументов.");
        }
        collection.clear();
        System.out.println("Коллекция очищена.");
    }
}
