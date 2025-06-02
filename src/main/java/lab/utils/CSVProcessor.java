package lab.utils;

import lab.data.*;
import lab.exceptions.InvalidDataException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class CSVProcessor {

    public static List<Organization> loadFromCSV(String filename) throws IOException, InvalidDataException {
        String inputFile = System.getenv("COLLECTION_FILE");
        List<Organization> organizations = new ArrayList<>();

        File file = new File(inputFile);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Ошибка: файл не найден!");
            return organizations;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    String name = parts[0];
                    try {
                        Coordinates coordinates = new Coordinates(Validator.parseXCoordinates(parts[1]),
                                Validator.parseYCoordinates(parts[2]));
                        long annualTurnover = Validator.parseAnnualTurnover(parts[3]);
                        OrganizationType type = Validator.parseOrganizationType(parts[4]);
                        Location location = new Location(Validator.parseXLocation(parts[6]), Validator.parseYLocation(parts[7]),
                                Validator.parseZLocation(parts[8]));
                        Address address = new Address(Validator.validateStreetName(parts[5]), location);
                        Organization org = new Organization(name, coordinates, annualTurnover, type, address);
                        organizations.add(org);
                    } catch (InvalidDataException ignore) {
                    }
                } else {
                    throw new InvalidDataException("Неверное количество аргументов: " + line);
                }
            }
        }
        return organizations;
    }

    public static void saveToCSV(String filename, TreeMap<Integer, Organization> collection) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Organization org : collection.values()) {
                String line;
                line = String.format("%d,%s,%f,%d,%s,%d,%s,%s",
                        org.getID(),
                        org.getName(),
                        org.getCoordinates().getX(),
                        org.getCoordinates().getY(),
                        org.getCreationDate(),
                        org.getAnnualTurnover(),
                        org.getType() != null ? org.getType().name() : "",
                        org.getOfficialAddress() != null ? org.getOfficialAddress().getStreet() : "");
                if (org.getOfficialAddress().getTown() != null) {
                    line += String.format("%f,%f,%d", org.getOfficialAddress().getTown().getX(),
                            org.getOfficialAddress().getTown().getY(),
                            org.getOfficialAddress().getTown().getZ());
                } else {
                    line += String.format("%s,%s,%s", "", "", "");
                }
                writer.println(line);
            }
        }
    }
}
