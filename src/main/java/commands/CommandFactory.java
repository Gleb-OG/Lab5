package commands;

import exceptions.InvalidDataException;
import interfaces.Command;


public class CommandFactory {
    public static Command createCommand(String input) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];

        return switch (commandName) {
            case "clear" -> new Clear();
            case "info" -> new Info();
            case "help" -> new Help();
            //Придумать, как реализовать чтение {element}, null и annualTurnover из строки
            //case "insert null {element}": return new InsertElement();
            //case "update id {element}": return new UpdateID();
            //case "remove_key null": return new RemoveKey();
            case "save" -> new Save();
            //case "execute_script file_name": return new ExecuteScript();
            case "exit" -> new Exit();
            //case "remove_greater {element}": return new RemoveGreater();
            //case "remove_lower_key null" return new RemoveLower();
            //case "replace_if_lowe null {element}": return new ReplaceIfLowe();
            case "sum_of_annual_turnover" -> new SumOfAnnualTurnover();
            //case "filter_by_annual_turnover annualTurnover": return new FilterByAnnualTurnover();
            //case "filter_greater_than_official_address officialAddress": return new FilterGreaterThanOfficialAddress();
            default -> throw new InvalidDataException("Неизвестная команда: " + commandName);
        };
    }
}
