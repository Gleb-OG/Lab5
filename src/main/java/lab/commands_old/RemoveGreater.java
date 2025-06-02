package lab.commands_old;

import lab.interfaces.CommandInterface;
import lab.data.Organization;
import lab.exceptions.InvalidDataException;
import lab.utils.InteractiveParser;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class RemoveGreater implements CommandInterface {
    @Override
    public void execute(String[] args) throws Exception {
        try {
            System.out.println("Введите данные организации для сравнения:");
            Organization organization = InteractiveParser.parseOrganization();

            TreeMap<Integer, Organization> collection = collectionManager.getCollection();
            Iterator<Map.Entry<Integer, Organization>> iterator = collection.entrySet().iterator();

            int removedCount = 0;
            while (iterator.hasNext()) {
                Map.Entry<Integer, Organization> entry = iterator.next();
                if (entry.getValue().compareTo(organization) > 0) {
                    iterator.remove();
                    removedCount++;
                }
            }

            System.out.println("Удалено элементов: " + removedCount);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Ошибка ввода: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}
