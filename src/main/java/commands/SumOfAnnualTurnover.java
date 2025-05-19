package commands;

import data.Organization;
import interfaces.Command;
import java.util.TreeMap;


public class SumOfAnnualTurnover implements Command {
    @Override
    public void execute(TreeMap<Integer, Organization> collection, String[] args) {
        long sum = collection.values().stream().mapToLong(org -> org.getAnnualTurnover()).sum();
        System.out.println("Сумма годового оборота всех организаций: " + sum);
    }
}
