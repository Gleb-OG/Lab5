package interfaces;

public interface Command {
    void execute(String[] args);
    void execute();
    void description();
}
