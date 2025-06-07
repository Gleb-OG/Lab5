package lab.commands;

import lab.Main;
import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.utils.Validator;
import java.io.IOException;


public class FilterByAnnualTurnover extends Command {

    public FilterByAnnualTurnover() {
        super("filter_by_annual_turnover <annual turnover>", "Вывод организации, годовой оборот которых равен введенному", 1);
    }

    @Override
    public void execute() throws InvalidDataException, IOException {
        String sizeOfAnnualTurnover = Main.console.getToken(1);
        long annualTurnover = Validator.parseAnnualTurnover(sizeOfAnnualTurnover);

        int count = 0;
        for (Organization organization : collectionManager.getCollection().values()) {
            if (organization.getAnnualTurnover() == annualTurnover) {
                System.out.println(organization);
                count++;
            }
        }
        if (count == 0 || collectionManager.getCollection().values().isEmpty()) {
            if (count == 0 && !collectionManager.getCollection().values().isEmpty()) {
                System.out.println("В коллекции отсутствуют организации, годовой оборот которых равен " + sizeOfAnnualTurnover + ".");
            } else {
                System.out.println("Коллекция пуста.");
            }
        } else {
            System.out.println("Все организации, годовой оборот которых равен " + sizeOfAnnualTurnover + ".");
        }
    }

    @Override
    public void execute(String[] args) throws InvalidDataException {
        if (args.length != 1) return;

        String annualTurnoverString = args[0];
        try {
            long annualTurnover = Validator.parseAnnualTurnover(annualTurnoverString);
            int count = 0;

            for (Organization organization : collectionManager.getCollection().values()) {
                if (organization.getAnnualTurnover() == annualTurnover) {
                    System.out.println(organization);
                    count++;
                }
            }

            if (count == 0 || collectionManager.getCollection().values().isEmpty()) {
                if (count == 0 && !collectionManager.getCollection().values().isEmpty()) {
                    System.out.println("В коллекции отсутствуют организации, годовой оборот которых равен " + annualTurnoverString + ".");
                } else {
                    System.out.println("Коллекция пуста.");
                }
            } else {
                System.out.println("Все организации, годовой оборот которых равен " + annualTurnoverString + ".");
            }
        } catch (InvalidDataException ignore) {return;
        }
    }

}