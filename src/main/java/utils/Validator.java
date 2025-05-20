package utils;

import data.OrganizationType;
import exceptions.InvalidDataException;


public class Validator {
    public static void validateOrganizationName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
    }

    public static Double parseXCoordinates(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
        try {
            Double value = Double.parseDouble(input);
            if (value <= -922) {
                throw new InvalidDataException("Число должно быть больше -922.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static long parseYCoordinates(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static float parseXLocation(String input) {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static double parseYLocation(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static Long parseZLocation(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static long parseAnnualTurnover(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
        try {
            long annualTurnover = Long.parseLong(input);
            if (annualTurnover <= 0) {
                throw new InvalidDataException("Число должно быть больше нуля.");
            }
            return annualTurnover;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static OrganizationType parseOrganizationType(String input) {
        if (input == null || input.isEmpty()) {
           return null;
        }
        try {
            return OrganizationType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Неизвестный тип организации: " + e.getMessage());
        }
    }

    public static void validateStreetName(String streetName) {
        if (streetName == null || streetName.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
        if (streetName.length() > 103) {
            throw new InvalidDataException("Длина названия улицы не может превышать 103 символа.");
        }
    }

    public static int validateInt(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Ключ не может быть пустым.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректно введено число.");
        }
    }

}
