package utils;

public class Validator {
    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            //throw new InvalidDataException("Имя не может быть пустым");
        }
    }
}
