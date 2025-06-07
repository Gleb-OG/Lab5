package lab.commands;

import lab.Main;
import lab.exceptions.InvalidDataException;
import lab.managers.KeyManager;


public class RemoveKey extends Command {

    public RemoveKey() {
        super("remove_key <key>", "Удаление элемента по ключу", 1);
    }

    @Override
    public boolean check(String[] args) {
        if (args.length != 1) return false;
        if (!args[0].matches("^\\d+$")) return false;

        int key = Integer.parseInt(args[0]);
        return collectionManager.getOrganizationByKey(key) != null;
    }



    @Override
    public void execute() {
        try {
            String removingKey = Main.console.getToken(1);
            if (!removingKey.matches("^\\d+$")) {
                throw new InvalidDataException("id должен быть строго больше нуля.");
            }
            int key = Integer.parseInt(removingKey);
            if (KeyManager.checkKeyExisting(key)) {
                collectionManager.removeOrganizationByKey(key);
                System.out.println("Элемент с ключом " + key + " удалён из коллекции.");
            } else {
                System.out.println("Элемента с ключом " + key + " не обнаружено в коллекции.");
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length != 1) {
                return;
            }

            String removingKey = args[0];

            if (!removingKey.matches("^\\d+$")) {
                return;
            }

            int id = Integer.parseInt(removingKey);
            collectionManager.removeOrganizationByKey(id);
        } catch (NumberFormatException ignored) {
        }
    }
}
