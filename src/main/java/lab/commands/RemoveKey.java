package lab.commands;

import lab.Main;
import lab.exceptions.InvalidDataException;

public class RemoveKey extends Command {

    public RemoveKey() {
        super("remove_key", "Удаление элемента по ключу", 1);
    }

    @Override
    public boolean check(String[] args) {
        if (args.length != 1) return false;
        if (!args[0].matches("^\\d+$")) return false;

        int id = Integer.parseInt(args[0]);
        return collectionManager.getOrganizationByID(id) != null;
    }



    @Override
    public void execute() {
        try {
            String removingID = Main.console.getToken(1);
            if (!removingID.matches("^\\d+$")) {
                throw new InvalidDataException("id должен быть строго больше нуля.");
            }
            int id = Integer.parseInt(removingID);
            collectionManager.removeOrganizationByID(id);
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

            String removingID = args[0];

            if (!removingID.matches("^\\d+$")) {
                return;
            }

            int id = Integer.parseInt(removingID);
            collectionManager.removeOrganizationByID(id);
        } catch (NumberFormatException ignored) {
        }
    }
}
