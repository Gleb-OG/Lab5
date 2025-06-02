package commands_old;


import interfaces.Command;

public class SumOfAnnualTurnover implements Command {
    @Override
    public void execute(String[] args) {
        long sum = collectionManager.getCollection().values()
                .stream().mapToLong(org -> org.getAnnualTurnover()).sum();
        System.out.println("Сумма годового оборота всех организаций: " + sum);
    }
}
