package commands_old;

import interfaces.Command;
import data.Organization;

import java.util.*;


public class Show implements Command {
    @Override
    public void execute(String[] args) {
        List<Organization> organizations = new ArrayList<>(collectionManager.getCollection().values());
        Collections.sort(organizations);
        organizations.forEach(org -> System.out.println(org.toString()));
    }
}
