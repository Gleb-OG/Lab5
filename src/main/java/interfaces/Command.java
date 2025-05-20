package interfaces;

import data.Organization;
import managers.CollectionManager;

import java.util.TreeMap;

public interface Command {
    void execute(CollectionManager collectionManager, String[] args) throws Exception;
}
