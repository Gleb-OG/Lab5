package lab.utils;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class InteractiveParser {
    private final Scanner scanner = new Scanner(System.in);

    public Organization wrap() {
        if (!Main.scriptMode) {
            Organization organization = new Organization();
            try {
                readOrganizationName(organization);
                readCoordinates(organization);
                readAnnualTurnover(organization);
                readOrganizationType(organization);
                readLocation(organization);
                readAddress(organization);

                System.out.println("Данные успешно собраны");
                return organization;
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            return null;
        } else {
            String name = collectString();
            if (name == null) return null;

            Integer x = collectInteger();
            if (x == null) return null;

            Long y = collectLong();
            if (y == null) return null;

            Long members = collectLong();
            if (members == null) return null;

            MusicGenre genre = collectMusicGenre();
            if (Main.scriptMode && genre == null) return null;

            String studio = collectString();
            if (studio == null) return null;

            String frontman = collectString();
            if (frontman == null) return null;

            MusicBand musicBand = new MusicBand();
            collectName(musicBand);
            collectCoordinates(musicBand);
            collectNumberOfParticipants(musicBand);
            collectMusicGenre(musicBand);
            collectStudio(musicBand);
            return musicBand;
        }
    }

    public String collectValue(Organization organization) throws InvalidDataException, IllegalArgumentException {
        String value = scanner.nextLine();
        if (value.trim().isEmpty()) {
            throw new InvalidDataException("Ошибка: введено пустое значение.");
        }
        return value;
    }

    public String collectString(Organization organization) {
        while (true) {
            try {
                return collectValue(organization).trim();
            } catch (InvalidDataException ex) {
                if (!Main.scriptMode) System.out.println("Значение этого поля не может быть пустым");
                else return null;
            }
        }
    }

    public String readOrganizationName(Organization organization) {
        while (true) {
            System.out.print("Введите название организации: ");
            String input = scanner.nextLine().trim();
            try {
                Validator.validateOrganizationName(input);
                return input;
            } catch (InvalidDataException ignored) {
            }
        }
    }

    public Coordinates readCoordinates(Organization organization) {
        while (true) {
            System.out.print("Введите координату Х: ");
            String inputX = scanner.nextLine().trim();
            System.out.print("Введите координату Y: ");
            String inputY = scanner.nextLine().trim();
            try {
                Double x = Validator.parseXCoordinates(inputX);
                long y = Validator.parseYCoordinates(inputY);
                return new Coordinates(x, y);
            } catch (InvalidDataException e) {
                System.out.println("Неверный формат ввода числа: оба поля должны быть числами, причем Х > -922.");
            }
        }
    }

    public long readAnnualTurnover(Organization organization) {
        while (true) {
            System.out.print("Введите годовой оборот: ");
            String input = scanner.nextLine().trim();
            try {
                return Validator.parseAnnualTurnover(input);
            } catch (InvalidDataException e) {
                System.out.println("Годовой оборот должен быть строго положительным числом.");
            }
        }
    }

    public OrganizationType readOrganizationType(Organization organization) {
        while (true) {
            System.out.print("Введите тип организации (COMMERCIAL, PUBLIC, GOVERNMENT, " +
                    "PRIVATE_LIMITED_COMPANY или нажмите Enter, если тип отсутствует): ");
            String input = scanner.nextLine().trim();
            try {
                return Validator.parseOrganizationType(input);
            } catch (InvalidDataException e) {
                System.out.println("Введите тип организации строго из приведенного списка.");
            }
        }
    }

    public Location readLocation(Organization organization) throws InvalidDataException {
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
            } catch (InvalidDataException e) {
                System.out.println("Неверный формат ввода числа: все координаты должны быть числами.");
            }
        }
    }

    public Address readAddress(Organization organization) throws InvalidDataException {
        while (true) {
            System.out.print("Введите название улицы (или нажмите Enter, если адрес отсутствует): ");
            String streetName = scanner.nextLine().trim();
            if (streetName.isEmpty()) return null;
            Location location = readLocation(organization);
            try {
                streetName = Validator.validateStreetName(streetName);
                if (location == null) {
                    return new Address(streetName);
                }
                return new Address(streetName, location);
            } catch (InvalidDataException e) {
                System.out.println("Длина названия улицы не может превышать 103 символа.");
            }
        }
    }

    public Organization parseOrganization() throws InvalidDataException {
        String name = readOrganizationName(organization);
        Coordinates coordinates = readCoordinates(organization);
        long annualTurnover = readAnnualTurnover(organization);
        OrganizationType type = readOrganizationType(organization);
        Address address = readAddress(organization);

        return new Organization(name, coordinates, annualTurnover, type, address);
    }
}