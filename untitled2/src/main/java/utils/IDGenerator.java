package utils;

public class IDGenerator {
    static int id = 1;

    public static int generateID() {
        return id++;
    }
}
