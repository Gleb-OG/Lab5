package lab.interfaces;

import java.io.IOException;

public interface CommandInterface {
    void execute(String[] args);
    void execute() throws IOException;
    void description();
}
