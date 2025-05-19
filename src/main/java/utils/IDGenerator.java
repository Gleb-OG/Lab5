package utils;

public class IDGenerator {
    private static int id = 1;

    public static void init(int lastID) {
        id = lastID + 1;
    }

    public static int generateID() {
        return id++;
    }
}
