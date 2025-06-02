package commands_old;

import interfaces.Command;
import data.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class Info implements Command {

    @Override
    public void execute(String[] args) {
        List<Organization> organizations = new ArrayList<>(collectionManager.getCollection().values());
        System.out.printf("""
                Информация о коллекции:
                Тип коллекции: %s
                Дата инициализации: %s
                Количество элементов в коллекции: %d""", TreeMap.class, collectionManager.getInitializationDate(),
                organizations.toArray().length);
    }
}
