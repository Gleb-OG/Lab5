package lab.managers;

import java.util.HashSet;


public class KeyManager {

    private static HashSet<Integer> usedKeys = new HashSet<>();

    public static int generateKey() {
        int newKey = 1;
        while (usedKeys.contains(newKey)) {
            newKey++;
        }
        usedKeys.add(newKey);
        return newKey;
    }

    public static void registerKey(int id) {
        if (usedKeys.contains(id)) {
            throw new IllegalArgumentException("ID " + id + " уже используется.");
        }
        usedKeys.add(id);
    }

    public static void releaseKey(int id) {
        usedKeys.remove(id);
    }
}
