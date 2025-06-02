package lab.commands;

public class Info extends Command {

    public Info() {
        super("info", "Информация о коллекции", 0);
    }

    @Override
    public void execute() {
        collectionManager.info();
    }
}
