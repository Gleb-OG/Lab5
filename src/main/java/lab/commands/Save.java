package lab.commands;

import lab.Main;
import lab.utils.CSVProcessor;
import java.io.IOException;

public class Save extends Command {

    public Save() {
        super("save", "Сохранение коллекции в файл", 0);
    }

    @Override
    public void execute() throws IOException {
        String filename = "Collection";
        CSVProcessor.saveToCSV(filename, Main.collectionManager.getCollection());
        System.out.println("Элементы успешно сохранены в файл");
    }
}
