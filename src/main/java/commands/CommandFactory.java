package commands;

import exceptions.InvalidDataException;
import interfaces.Command;

public class CommandFactory {
    public static Command createCommand(String commandName) {
        switch (commandName) {
            case "clear": return new Clear();
            case "info": return new Info();
            case "help": return new Help();
            //Придумать, как реализовать чтение {element}, null и annualTurnover из строки
            //case "insert null {element}": return new InsertElement();
            //case "update id {element}": return new UpdateID();
            //case "remove_key null": return new RemoveKey();
            case "save": return new Save();
            //case "execute_script file_name": return new ExecuteScript();
            case "exit": return new Exit();
            //case "remove_greater {element}": return new RemoveGreater();
            //case "remove_lower_key null" return new RemoveLower();
            //case "replace_if_lowe null {element}": return new ReplaceIfLowe();
            case "sum_of_annual_turnover": return new SumOfAnnualTurnover();
            //case "filter_by_annual_turnover annualTurnover": return new FilterByAnnualTurnover();
            //case "filter_greater_than_official_address officialAddress": return new FilterGreaterThanOfficialAddress();
            default: throw new InvalidDataException("Неизвестная команда: " + commandName);
        }
    }
}
