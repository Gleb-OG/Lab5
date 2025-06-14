package lab.commands;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import lab.managers.KeyManager;
import lab.utils.IDGenerator;
import lab.utils.InteractiveParser;
import lab.utils.Validator;
import java.util.TreeMap;


public class ReplaceIfLower extends Command {

    public ReplaceIfLower() {
        super("replace_if_lower <key>", "Замена элемента по ключу, если годовой оборот новой организации меньше старого", 1);
    }

    @Override
    public int getArgsAmount() {
        return Main.scriptMode ? 10 : 1;
    }

    @Override
    public boolean check(String[] args) {
        if (args.length != 10) return false;
        if (!args[0].matches("^\\d+$")) return false;

        int id = Integer.parseInt(args[0]);
        return IDGenerator.checkIdExisting(id);
    }

    @Override
    public void execute() {
        try {
            String updatingKey = Main.console.getToken(1);
            if (!updatingKey.matches("^\\d+$")) {
                throw new InvalidDataException("Ключ может быть только натуральным числом.");
            }
            int key = Validator.validateInt(updatingKey);

            if (KeyManager.checkKeyExisting(key)) {
                TreeMap<Integer, Organization> collection = collectionManager.getCollection();
                InteractiveParser parser = new InteractiveParser();
                try {
                    Organization oldOrganization = collectionManager.getOrganizationByKey(key);
                    if (oldOrganization != null) {
                        Organization newOrganization = parser.parseOrganization();

                        if (collection.get(key).getAnnualTurnover() > newOrganization.getAnnualTurnover()) {
                            collectionManager.removeOrganizationByKey(key);
                            collectionManager.addOrganization(key, newOrganization);
                            System.out.println("Элемент с ключом " + key + " успешно обновлен.");
                        } else {
                            System.out.println("Элемент с ключом " + key + " не был обновлен, " +
                                    "так как у введенной организации годовой оборот больше или равен нынешнему.");
                        }
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Введите натуральное число.");
                } catch (InvalidDataException ignore) {
                }
            } else {
                System.out.println("Элемент с ключом " + key + " отсутствует.");
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 10) {
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            TreeMap<Integer, Organization> collection = collectionManager.getCollection();
            int key = 0;
            for (int k : collection.keySet()) if (collection.get(k).getID() == id) key = k;
            if (key == 0) return;
            Organization existOrg = collectionManager.getOrganizationByKey(key);
            if (existOrg == null) {
                return;
            }

            String name = args[1].trim();
            if (name.isEmpty()) {
                return;
            }

            Double xCoordinates;
            long yCoordinates;
            float xLocation;
            double yLocation;
            Long zLocation;
            long annualTurnover;

            try {
                xCoordinates = Validator.parseXCoordinates(args[2]);
            } catch (InvalidDataException e) {
                return;
            }

            try {
                yCoordinates = Validator.parseYCoordinates(args[3]);
            } catch (NumberFormatException e) {
                return;
            }

            try {
                annualTurnover = Validator.parseAnnualTurnover(args[4]);
            } catch (InvalidDataException e) {
                return;
            }

            String streetName;
            try {
                streetName = Validator.validateStreetName(args[6].trim());
            } catch (InvalidDataException e) {
                return;
            }

            try {
                xLocation = Validator.parseXLocation(args[7]);
            } catch (InvalidDataException e) {
                return;
            }

            try {
                yLocation = Validator.parseYLocation(args[8]);
            } catch (InvalidDataException e) {
                return;
            }

            try {
                zLocation = Validator.parseZLocation(args[9]);
            } catch (InvalidDataException e) {
                return;
            }

            OrganizationType type = null;
            if (!args[5].equalsIgnoreCase("null")) {
                try {
                    type = OrganizationType.valueOf(args[5].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return;
                }
            }

            Coordinates coordinates = new Coordinates();
            coordinates.setX(xCoordinates);
            coordinates.setY(yCoordinates);

            Address address = new Address();
            Location location = new Location();
            location.setX(xLocation);
            location.setY(yLocation);
            location.setZ(zLocation);
            address.setStreet(streetName);
            address.setLocation(location);

            Organization organization = new Organization();
            organization.setName(name);
            organization.setCoordinates(coordinates);
            organization.setAnnualTurnover(annualTurnover);
            organization.setType(type);
            organization.setOfficialAddress(address);

            collectionManager.removeOrganizationByKey(key);
            collectionManager.addOrganization(key, organization);
            System.out.println("Организация с id " + id + " обновлена успешно.");
        } catch (Exception ignored) {
        }
    }
}
