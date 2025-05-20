package commands;

import data.Organization;
import interfaces.Command;
import managers.CollectionManager;
import java.util.*;


public class Show implements Command {
    @Override
    public void execute(CollectionManager collectionManager, String[] args) {
        List<Organization> organizations = new ArrayList<>(collectionManager.getCollection().values());
        Collections.sort(organizations);
        organizations.forEach(org -> System.out.println(org.toString()));
    }
}
