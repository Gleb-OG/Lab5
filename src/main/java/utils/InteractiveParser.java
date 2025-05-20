package utils;

import data.Address;
import data.Coordinates;
import data.Organization;
import data.OrganizationType;
import exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InteractiveParser {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readName() {
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

    public static Coordinates readCoordinates() {
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

    public static long readAnnualTurnover() {
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

    public static OrganizationType readOrganizationType() {
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

    public static List<String> readLocation() {
        while (true) {
            System.out.print("Введите координату X адреса (или нажмите Enter, если координаты отсутствуют): ");
            String inputX = scanner.nextLine().trim();
            if (inputX.isEmpty()) return null;
            System.out.print("Введите координату Y адреса: ");
            String inputY = scanner.nextLine().trim();
            System.out.print("Введите координату Z адреса: ");
            String inputZ = scanner.nextLine().trim();
            try {
                List<String> location = new ArrayList<>();
                location.add(inputX);
                location.add(inputY);
                location.add(inputZ);
                return location;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Address readAddress() {
        while (true) {
            System.out.print("Введите название улицы (или нажмите Enter, если адрес отсутствует): ");
            String streetName = scanner.nextLine().trim();
            if (streetName.isEmpty()) return null;
            List<String> location = readLocation();
            try {
                if (location == null) return new Address(streetName);
                return new Address(streetName, location.get(0), location.get(1), location.get(2));
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Organization parseOrganization() {
        String name = InteractiveParser.readName();
        Coordinates coordinates = InteractiveParser.readCoordinates();
        long annualTurnover = InteractiveParser.readAnnualTurnover();
        OrganizationType type = InteractiveParser.readOrganizationType();
        Address address = InteractiveParser.readAddress();

        return new Organization(name, coordinates, annualTurnover, type, address);
    }
}