package lab.commands;

import lab.Main;
import lab.exceptions.InvalidDataException;
import lab.managers.ScriptManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lab.exceptions.WrongArgsNumber;


public class ExecuteScript extends Command {
    List<String> list = Arrays.asList("insert", "update_id", "remove_key",
            "replace_if_lower", "execute_script", "remove_greater",
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

            try (BufferedReader reader = scriptManager.getBufferedReader()) {
                Main.currentScriptReader = reader;
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;

                    String[] tokens = Arrays.stream(line.split(" "))
                            .filter(s -> !s.isEmpty())
                            .toArray(String[]::new);

                    Main.console.setTokens(tokens);

                    Command command = Main.inv.commands.get(tokens[0]);
                    if (command == null) {
                        continue;
                    }

                    if ("execute_script".equals(command.getNameOfCommand())) {
                        break;
                    }

                    if (tokens.length - 1 != command.getArgsAmount()) {
                        continue;
                    }

                    try {
                        if (command.check(Arrays.copyOfRange(tokens, 1, tokens.length))) {
                            System.out.println("Выполнение команды: " + line);
                        }
                        if (list.contains(tokens[0])) {
                            command.execute(Arrays.copyOfRange(tokens, 1, tokens.length));
                        }
                        else {
                            command.execute();
                        }
                    } catch (WrongArgsNumber | InvalidDataException e) {
                        System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                    }
                }
            }

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