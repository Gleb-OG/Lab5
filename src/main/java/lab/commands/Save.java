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
        try {
        CSVProcessor.saveToCSV(Main.filename, Main.collectionManager.getCollection());
        System.out.println("Элементы успешно сохранены в файл");
        } catch (IOException e) {
            throw new IOException("Доступ к файлу отсутствует");
        }
    }
}
