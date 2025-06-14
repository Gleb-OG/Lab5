package lab.commands;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import lab.utils.CSVProcessor;
import lab.utils.InteractiveParser;
import lab.utils.Validator;

/**
 * Команда, вставляющая введенную организацию по ключу.
 * Если ключ уже занят другой организацией, то значение по данном ключу перезаписывается новым.
 */
public class InsertElement extends Command {

    public InsertElement() {
        super("insert <key>", "Добавление элемента с заданным ключом", 1);
    }

    @Override
    public int getArgsAmount() {
        return Main.scriptMode ? 2 : 1;
    }

    @Override
    public boolean check(String[] args) {
        return args[0].matches("^\\d+$");
    }

    @Override
    public void execute() throws InvalidDataException {
        try {
            String updatingKey = Main.console.getToken(1);
            int key = Validator.validateInt(updatingKey);

            InteractiveParser parser = new InteractiveParser();
            Organization organization = parser.parseOrganization();
            if (collectionManager.getCollection().containsKey(key)) {
                collectionManager.removeOrganizationByKey(key);
            }
            collectionManager.addOrganization(key, organization);
            System.out.println("Элемент успешно добавлен в коллекцию по ключу " + key);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) throws InvalidDataException {
        int key = Integer.parseInt(args[0]);
        Organization organization = CSVProcessor.parseOrganizationFromString(args[1]);
        if (collectionManager.getCollection().containsKey(key)) {
            collectionManager.removeOrganizationByKey(key);
        }
        collectionManager.addOrganization(key, organization);
        System.out.println("Элемент успешно добавлен в коллекцию по ключу " + key);
    }
}