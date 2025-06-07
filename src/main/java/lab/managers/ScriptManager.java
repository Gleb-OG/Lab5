package lab.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScriptManager {

    private final String filePath;

    public ScriptManager(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readScript() throws IOException {
        List<String> scriptLines = new ArrayList<>();
        File scriptFile = new File(filePath);

        if (!scriptFile.exists()) {
            throw new IOException("Файл скрипта не найден: " + filePath);
        }

        if (!scriptFile.canRead()) {
            throw new IOException("Нет прав на чтение файла: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("#")) {
                    scriptLines.add(line.trim());
                }
            }
        }

        return scriptLines;
    }

    public BufferedReader getBufferedReader() throws IOException {
        File scriptFile = new File(filePath);

        if (!scriptFile.exists()) {
            throw new IOException("Файл скрипта не найден: " + filePath);
        }

        if (!scriptFile.canRead()) {
            throw new IOException("Нет прав на чтение файла: " + filePath);
        }

        return new BufferedReader(new FileReader(scriptFile));
    }
}
