package commands_old;


import interfaces.Command;

public class Exit implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }
}