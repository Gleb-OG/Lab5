package lab.commands;

import lab.Main;
import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.utils.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super("remove_greater", "Удаление элементов, превышающих введенный", 1);
    }

    @Override
    public void execute() throws IOException {
        if (!Main.scriptMode) {
            try {
                String sizeOfAnnualTurnoverString = Main.console.getToken(1);

                if (!sizeOfAnnualTurnoverString.matches("^\\d+$")) {
                    throw new InvalidDataException("Это поле может быть только числом.");
                }
                long sizeOfAnnualTurnover = Validator.parseAnnualTurnover(sizeOfAnnualTurnoverString);
                Organization compareOrg = new Organization();
                compareOrg.setAnnualTurnover(sizeOfAnnualTurnover);

                HashSet<Organization> orgsToRemove = new HashSet<>();
                int countToRemove = 0;
                for (Organization organization : collectionManager.getCollection().values()) {
                    if (organization.compareTo(compareOrg) < 0) {
                        orgsToRemove.add(organization);
                        countToRemove++;
                    }
                }

                if (countToRemove == 0 || collectionManager.getCollection().values().isEmpty()) {
                    if (countToRemove == 0 && ! collectionManager.getCollection().values().isEmpty()) {
                        System.out.println("Нет организаций, у которых годовой оборот меньше чем " + sizeOfAnnualTurnoverString);
                    } else System.out.println("Коллекция пуста.");
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    collectionManager.getCollection().values().forEach(x -> list.add(x.getID()));
                    list.forEach(x -> collectionManager.getCollection().remove(x));
                    System.out.println("Удалено " + orgsToRemove.size() + " организаций с годовым оборотом меньше чем " + sizeOfAnnualTurnoverString);
                }
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Слишком большое число.");
            }
        }
    }
}
