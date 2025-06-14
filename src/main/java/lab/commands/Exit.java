package lab.commands;

/**
 * Команда, завершающая работу программы без сохранения.
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "Завершение программы без сохранения в файл", 0);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}