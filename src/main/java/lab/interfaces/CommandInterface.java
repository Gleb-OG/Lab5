package lab.interfaces;

import lab.exceptions.InvalidDataException;

import java.io.IOException;

public interface CommandInterface {
    void execute(String[] args);
    void execute() throws IOException, InvalidDataException;
    void description();
}
