package lab.commands_old;

import lab.interfaces.CommandInterface;
import lab.exceptions.FileAccessException;
import lab.utils.CSVProcessor;
import java.io.PrintWriter;


public class Save implements CommandInterface {
    private final String filename;

    public Save(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(String[] args) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            CSVProcessor.saveToCSV(filename, collectionManager.getCollection());

            System.out.println("Коллекция успешно сохранена в файл: " + filename);
        } catch (SecurityException e) {
            throw new FileAccessException("Нет прав на запись в файл: " + filename);
        } catch (Exception e) {
            throw new FileAccessException("Ошибка при сохранении: " + e.getMessage());
        }
    }
}