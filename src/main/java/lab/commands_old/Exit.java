package lab.commands_old;


import lab.interfaces.CommandInterface;

public class Exit implements CommandInterface {
    @Override
    public void execute(String[] args) {
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }
}