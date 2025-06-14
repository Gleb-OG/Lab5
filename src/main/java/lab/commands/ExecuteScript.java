package lab.commands;

import lab.Main;
import lab.managers.ScriptManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Команда, запускающая скрипт из файла.
 */
public class ExecuteScript extends Command {
    List<String> list = Arrays.asList("insert", "update", "remove_key",
            "replace_if_lower", "remove_greater", "remove_lower_key",
            "filter_greater_than_official_address", "filter_by_annual_turnover");


    public ExecuteScript() {
        super("execute_script <filename>", "Считывание и исполнение скрипта из указанного файла.", 1);
    }

    @Override
    public void execute() {
        Main.scriptMode = true;
        try {
            String file = Main.console.getToken(1);
            ScriptManager scriptManager = new ScriptManager(file);
            Main.runManager.runScript(scriptManager, list);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Не указано имя файла для выполнения скрипта.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении скрипта: " + e.getMessage());
        } finally {
            Main.scriptMode = false;
            Main.currentScriptReader = null;
        }
    }
}