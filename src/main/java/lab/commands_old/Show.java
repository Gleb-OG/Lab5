package lab.commands_old;

import lab.interfaces.CommandInterface;
import lab.data.Organization;

import java.util.*;


public class Show implements CommandInterface {
    @Override
    public void execute(String[] args) {
        List<Organization> organizations = new ArrayList<>(collectionManager.getCollection().values());
        Collections.sort(organizations);
        organizations.forEach(org -> System.out.println(org.toString()));
    }
}
