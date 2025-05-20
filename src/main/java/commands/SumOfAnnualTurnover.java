package commands;

import interfaces.Command;
import managers.CollectionManager;


public class SumOfAnnualTurnover implements Command {
    @Override
    public void execute(CollectionManager collectionManager, String[] args) {
        long sum = collectionManager.getCollection().values()
                .stream().mapToLong(org -> org.getAnnualTurnover()).sum();
        System.out.println("Сумма годового оборота всех организаций: " + sum);
    }
}
