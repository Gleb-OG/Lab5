package lab.commands;

import static lab.Main.inv;

/**
 * Выводит справку по доступным командам.
 */
public class Help extends Command {

    public Help() {
        super("help", "Справка по доступным командам", 0);
    }

    @Override
    public void execute() {
        for (Command command : inv.getCommands().values()) {
            System.out.println("- " + command.nameOfCommand + ": " + command.getDescription());
        }
    }
}
