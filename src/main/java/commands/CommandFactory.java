package commands;

import exceptions.InvalidDataException;
import interfaces.Command;


public class CommandFactory {
    public static Command createCommand(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Пустая команда.");
        }

        String[] parts = input.split(" ");
        String commandName = parts[0].trim().toLowerCase();
        String[] args = (parts.length > 1) ? parts[1].split(" ") : new String[0];

        try {
            return switch (commandName) {
                case "clear" -> new Clear();
                case "info" -> new Info();
                case "help" -> new Help();
                case "insert" -> new InsertElement(args);
                case "update" -> new UpdateID(args);
                case "save" -> new Save();
                case "execute_script" -> new ExecuteScript();
                case "exit" -> new Exit();
                case "remove_greater" -> new RemoveGreater();
                case "remove_lower_key" -> new RemoveLower(args);
                case "replace_if_lowe" -> new ReplaceIfLowe();
                case "sum_of_annual_turnover" -> new SumOfAnnualTurnover();
                case "filter_by_annual_turnover" -> new FilterByAnnualTurnover();
                case "filter_greater_than_official_address" -> new FilterGreaterThanOfficialAddress();
                default -> throw new InvalidDataException("Неизвестная команда: " + commandName);
            };
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Неизвестная команда или некорректные аргументы");
        }
    }
}
