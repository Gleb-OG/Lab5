package commands;

import interfaces.Command;
import managers.CollectionManager;
import utils.CSVProcessor;


public class Save implements Command {
    @Override
    public void execute(CollectionManager collectionManager, String[] args) throws Exception {
        CSVProcessor.saveToCSV("Collection", collectionManager.getCollection());
    }
}
