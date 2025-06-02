package managers;


import interfaces.Command;;

public abstract class CommandManager implements Command {

//    protected static CollectionManager cm = Main.cm;
    protected String nameOfCommand;
    protected String description;
    protected int argsAmount;

    public CommandManager(String name, String description, int argsAmount) {
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

//    @Override
//    public void execute() {
//        Main.hc.addCommand(getNameOfCommand());
//    }

    @Override
    public void execute(String[] args) {
    }

    @Override
    public void description() {
        System.out.println(nameOfCommand + " - " + description);
    }
}