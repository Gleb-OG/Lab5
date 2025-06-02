package utils;

import data.*;
import exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InteractiveParser {
    private final Scanner scanner = new Scanner(System.in);

    public String readName() {
        while (true) {
            System.out.print("Название организации (не может быть пустым): ");
            String input = scanner.nextLine().trim();
            try {
                Validator.validateOrganizationName(input);
                return input;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
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
                return new Coordinates(inputX, inputY);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public long readAnnualTurnover() {
        while (true) {
            System.out.print("Введите годовой оборот (Должен быть > 0): ");
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
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> readLocation() throws InvalidDataException {
        while (true) {
            System.out.print("Введите координату X адреса (или нажмите Enter, если координаты отсутствуют): ");
            String inputX = scanner.nextLine().trim();
            if (inputX.isEmpty()) return null;
            float x = Validator.parseXLocation(inputX);
            System.out.print("Введите координату Y адреса: ");
            String inputY = scanner.nextLine().trim();
            double y = Validator.parseYLocation(inputY);
            System.out.print("Введите координату Z адреса: ");
            String inputZ = scanner.nextLine().trim();
            List location = new ArrayList<>();
            location.add(x);
            location.add(inputY);
            location.add(inputZ);
            return location;
        }
    }

    public Address readAddress() {
        while (true) {
            System.out.print("Введите название улицы (или нажмите Enter, если адрес отсутствует): ");
            String streetName = scanner.nextLine().trim();
            if (streetName.isEmpty()) return null;
            List<String> locationCoordinates = readLocation();
            Location location = new Location(locationCoordinates.get(0),
                    locationCoordinates.get(1), locationCoordinates.get(2));
            try {
                streetName = Validator.validateStreetName(streetName);
                if (location == null) return new Address(streetName);
                return new Address(streetName, location);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Organization parseOrganization() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        long annualTurnover = readAnnualTurnover();
        OrganizationType type = readOrganizationType();
        Address address = readAddress();

        return new Organization(name, coordinates, annualTurnover, type, address);
    }
}