package commands;

import interfaces.Command;

public class Help implements Command {
    @Override
    public void execute(String[] args) throws Exception {
        System.out.println("Доступные команды: help, info, show, insert *key* {element}, update *id* {element}, \n" +
                "remove_key *key*, clear, save, execute_script *file_name*, exit, remove_greater {element}, \n" +
                "replace_if_lowe *key* {element}, remove_lower_key *key*, sum_of_annual_turnover, \n" +
                "filter_by_annual_turnover *annualTurnover*, filter_greater_than_official_address *officialAddress*.");
    }
}
