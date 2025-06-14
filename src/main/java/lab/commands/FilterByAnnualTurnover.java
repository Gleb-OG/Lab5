package lab.commands;

import lab.Main;
import lab.exceptions.InvalidDataException;
import lab.utils.Validator;
import java.io.IOException;


public class FilterByAnnualTurnover extends Command {

    public FilterByAnnualTurnover() {
        super("filter_by_annual_turnover <annual turnover>", "Вывод организаций, годовой оборот которых равен введенному", 1);
    }

    @Override
    public boolean check(String[] args) {
        return args[0].matches("^\\d+$");
    }

    @Override
    public void execute() throws InvalidDataException, IOException {
        try {
            String sizeOfAnnualTurnover = Main.console.getToken(1);
            long annualTurnover = Validator.parseAnnualTurnover(sizeOfAnnualTurnover);

            int count = 0;
            for (int key : collectionManager.getCollection().keySet()) {
                if (collectionManager.getCollection().get(key).getAnnualTurnover() == annualTurnover) {
                    System.out.println("-------Organization-------" + "\nkey = " + key + "\n" +
                            collectionManager.getCollection().get(key));
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
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) throws InvalidDataException {
        long annualTurnover = Long.parseLong(args[0]);
        int count = 0;

        for (int key : collectionManager.getCollection().keySet()) {
            if (collectionManager.getCollection().get(key).getAnnualTurnover() == annualTurnover) {
                System.out.println("-------Organization-------" + "\nkey = " + key + "\n" +
                        collectionManager.getCollection().get(key));
                count++;
            }
        }

        if (count == 0 || collectionManager.getCollection().values().isEmpty()) {
            if (count == 0 && !collectionManager.getCollection().values().isEmpty()) {
                System.out.println("В коллекции отсутствуют организации, годовой оборот которых равен " + annualTurnover + ".");
            } else {
                System.out.println("Коллекция пуста.");
            }
        } else {
            System.out.println("Все организации, годовой оборот которых равен " + annualTurnover + ".");
        }
    }
}