package lab.commands_old;


import lab.interfaces.CommandInterface;

public class SumOfAnnualTurnover implements CommandInterface {
    @Override
    public void execute(String[] args) {
        long sum = collectionManager.getCollection().values()
                .stream().mapToLong(org -> org.getAnnualTurnover()).sum();
        System.out.println("Сумма годового оборота всех организаций: " + sum);
    }
}
