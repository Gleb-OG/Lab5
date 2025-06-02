package lab.utils;

import lab.data.*;
import lab.exceptions.InvalidDataException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class CSVProcessor {
    String inputFile = System.getenv("COLLECTION_FILE");
    public static List<Organization> loadFromCSV(String filename) throws IOException, InvalidDataException {
        List<Organization> organizations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9 || parts.length == 7 || parts.length == 6) {
                String name = parts[0];
                Coordinates coordinates = new Coordinates(parts[1], parts[2]);
                String annualTurnover = parts[3];
                String type = parts[4];
                if (parts.length == 6 && (parts[5] == null || parts[5].isEmpty())) {
                    Organization org = new Organization(name, coordinates, annualTurnover, type, null);
                    organizations.add(org);
                }
                if (parts.length == 7 && parts[5] != null &&
                        (parts[6] == null || parts[6].isEmpty())) {
                    Address address = new Address(parts[5]);
                    Organization org = new Organization(name, coordinates, annualTurnover, type, address);
                    organizations.add(org);
                } else {
                    Address address = new Address(parts[5], parts[6], parts[7], parts[8]);
                    Organization org = new Organization(name, coordinates, annualTurnover, type, address);
                    organizations.add(org);
                }

                } else {
                    throw new InvalidDataException("Некорректный формат строки: " + line);
                }
            }
        }
        return organizations;
    }

    public static void saveToCSV(String filename, TreeMap<Integer, Organization> collection) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Organization org : collection.values()) {
                String line;
                if (org.getOfficialAddress().getTown() != null) {
                    line = String.format("%d,%s,%f,%d,%s,%d,%s,%s,%f,%f,%d",
                            org.getID(),
                            org.getName(),
                            org.getCoordinates().getX(),
                            org.getCoordinates().getY(),
                            org.getCreationDate(),
                            org.getAnnualTurnover(),
                            org.getType() != null ? org.getType().name() : "",
                            org.getOfficialAddress().getStreet(),
                            org.getOfficialAddress().getTown().getX(),
                            org.getOfficialAddress().getTown().getY(),
                            org.getOfficialAddress().getTown().getZ()
                    );
                } else {
                    line = String.format("%d,%s,%f,%d,%s,%d,%s,%s",
                            org.getID(),
                            org.getName(),
                            org.getCoordinates().getX(),
                            org.getCoordinates().getY(),
                            org.getCreationDate(),
                            org.getAnnualTurnover(),
                            org.getType() != null ? org.getType().name() : "",
                            ""
                    );
                }
                writer.println(line);
            }
        }
    }
}
