package lab.commands;

import lab.Main;
import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.utils.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super("remove_greater", "Удаление элементов, превышающих введенный(сравнение по годовому обороту)", 1);
    }

    @Override
    public void execute() throws IOException {
        if (!Main.scriptMode) {
            try {
                System.out.println("Введите годовой оборот организации для сравнения:");
                String sizeOfAnnualTurnoverString = Main.console.getToken(1);

                if (!sizeOfAnnualTurnoverString.matches("^\\d+$")) {
                    throw new InvalidDataException("Это поле может быть только числом.");
                }
                long sizeOfAnnualTurnover = Validator.parseAnnualTurnover(sizeOfAnnualTurnoverString);
                Organization compareOrg = new Organization();
                compareOrg.setAnnualTurnover(sizeOfAnnualTurnover);

                Iterator<Map.Entry<Integer, Organization>> iterator = collectionManager.getCollection().entrySet().iterator();

                int countToRemove = 0;
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Organization> entry = iterator.next();
                    if (entry.getValue().compareTo(compareOrg) > 0) {
                        iterator.remove();
                        countToRemove++;
                    }
                }

                if (countToRemove == 0 || collectionManager.getCollection().values().isEmpty()) {
                    if (countToRemove == 0 && ! collectionManager.getCollection().values().isEmpty()) {
                        System.out.println("Нет организаций, у которых годовой оборот меньше чем " + sizeOfAnnualTurnoverString);
                    } else System.out.println("Коллекция пуста.");
                } else {
                    System.out.println("Удалено " + countToRemove +
                            " организаций с годовым оборотом меньше чем " + sizeOfAnnualTurnoverString + ".");
                }
            } catch (InvalidDataException e) {
                System.out.println("Это поле может быть только числом.");
            } catch (NumberFormatException e) {
                System.out.println("Слишком большое число.");
            }
        }
    }
}