package lab.commands;

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

    }
}
