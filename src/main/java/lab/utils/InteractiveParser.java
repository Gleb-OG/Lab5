package lab.utils;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InteractiveParser {
    private final Scanner scanner = new Scanner(System.in);

    public String collectValue() throws InvalidDataException, IllegalArgumentException {
        String value = scanner.nextLine();
        if (value.trim().isEmpty()) {
            throw new InvalidDataException("Ошибка: введено пустое значение.");
        }
        return value;
    }

    public String collectString() {
        while (true) {
            try {
                return collectValue().trim();
            } catch (InvalidDataException ex) {
                if (!Main.scriptMode) System.out.println("Значение этого поля не может быть пустым");
                else return null;
            }
        }
    }

    public String readOrganizationName() {
        while (true) {
            System.out.print("Введите название организации:");
            String input = scanner.nextLine().trim();
            try {
                Validator.validateOrganizationName(input);
                return input;
            } catch (InvalidDataException ignored) {
            }
        }
    }

    public Coordinates readCoordinates() {
        while (true) {
            System.out.println("Введите координату Х:");
            String inputX = scanner.nextLine().trim();
            System.out.println("Введите координату Y:");
            String inputY = scanner.nextLine().trim();
            try {
                Double x = Validator.parseXCoordinates(inputX);
                long y = Validator.parseYCoordinates(inputY);
                return new Coordinates(x, y);
            } catch (InvalidDataException ignored) {
            }
        }
    }

    public long readAnnualTurnover() {
        while (true) {
            System.out.print("Введите годовой оборот: ");
            String input = scanner.nextLine().trim();
            try {
                return Validator.parseAnnualTurnover(input);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public OrganizationType readOrganizationType() {
        while (true) {
            System.out.print("Введите тип организации (COMMERCIAL, PUBLIC, GOVERNMENT, " +
                    "PRIVATE_LIMITED_COMPANY или нажмите Enter, если тип отсутствует): ");
            String input = scanner.nextLine().trim();
            try {
                return Validator.parseOrganizationType(input);
            } catch (InvalidDataException ignored) {
            }
        }
    }

    public Location readLocation() throws InvalidDataException {
        while (true) {
            try {
                System.out.print("Введите координату X адреса (или нажмите Enter, если координаты отсутствуют): ");
                String inputX = scanner.nextLine().trim();
                if (inputX.isEmpty()) return null;
                float x = Validator.parseXLocation(inputX);
                System.out.print("Введите координату Y адреса: ");
                String inputY = scanner.nextLine().trim();
                double y = Validator.parseYLocation(inputY);
                System.out.print("Введите координату Z адреса: ");
                String inputZ = scanner.nextLine().trim();
                Long z = Validator.parseZLocation(inputZ);
                return new Location(x, y, z);
            } catch (InvalidDataException ignore) {
            }
        }
    }

    public Address readAddress() throws InvalidDataException {
        while (true) {
            System.out.print("Введите название улицы (или нажмите Enter, если адрес отсутствует): ");
            String streetName = scanner.nextLine().trim();
            if (streetName.isEmpty()) return null;
            Location location = readLocation();
            try {
                streetName = Validator.validateStreetName(streetName);
                if (location == null) {
                    return new Address(streetName);
                }
                return new Address(streetName, location);
            } catch (InvalidDataException ignore) {
            }
        }
    }

    public Organization parseOrganization() throws InvalidDataException {
        String name = readOrganizationName();
        Coordinates coordinates = readCoordinates();
        long annualTurnover = readAnnualTurnover();
        OrganizationType type = readOrganizationType();
        Address address = readAddress();

        return new Organization(name, coordinates, annualTurnover, type, address);
    }
}
