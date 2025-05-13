package utils;

import data.OrganizationType;
import exceptions.InvalidDataException;


public class Validator {
    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
    }

    public static double parseDoubleForCoordinates(String input) {
        try {
            double value = Double.parseDouble(input);
            if (value <= -922) {
                throw new InvalidDataException("Число должно быть больше -922.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static long validateAnnualTurnover(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Это поле не может быть пустым.");
        }
        try {
            long annualTurnover = Long.parseLong(input);
            if (annualTurnover <= 0) {
                throw new InvalidDataException("Число должно быть больше нуля.");
            }
            return annualTurnover;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректный формат данных: " + e.getMessage());
        }
    }

    public static OrganizationType validateOrganizationType(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Поле не может быть пустым.");
        }
        try {
            return OrganizationType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неизвестный тип организации: " + e.getMessage());
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

    public static long validateAndParseLongForLocation(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidDataException("Это поле не может быть пустым.");
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Некорректный формат данных: " + e.getMessage());
        }
    }
}
