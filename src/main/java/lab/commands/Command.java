package lab.commands;

import lab.Main;
import lab.interfaces.CommandInterface;
import lab.managers.CollectionManager;


public abstract class Command implements CommandInterface {

    protected static CollectionManager collectionManager = Main.collectionManager;
    protected String nameOfCommand;
    protected String description;
    protected int argsAmount;

    public Command(String name, String description, int argsAmount) {
        this.nameOfCommand = name;
        this.description = description;
        this.argsAmount = argsAmount;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }

    public String getDescription() {
        return description;
    }

    public int getArgsAmount() {
        return argsAmount;
    }

    public boolean check(String[] args) {
        return true;
    }

    public void execute(String[] args) {
    }

    @Override
    public void description() {
        System.out.println(nameOfCommand + " - " + description);
    }
}