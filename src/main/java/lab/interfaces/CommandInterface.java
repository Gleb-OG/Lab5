package lab.interfaces;

import lab.exceptions.InvalidDataException;

import java.io.IOException;

/**
 * Интерфейс паттерна command
 */
public interface CommandInterface {
    void execute(String[] args) throws InvalidDataException;
    void execute() throws IOException, InvalidDataException;
    void description();
}
