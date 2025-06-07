package lab.commands;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import lab.utils.InteractiveParser;
import lab.utils.Validator;


public class InsertElement extends Command {

    public InsertElement() {
        super("insert <key>", "Добавление элемента с заданным ключом", 1);
    }

    @Override
    public int getArgsAmount() {
        return Main.scriptMode ? 10 : 1;
    }

    @Override
    public boolean check(String[] args) {
        if (args.length != 10) return false;

        String name = args[0];
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        try {
            Validator.parseXCoordinates(args[1]);
            Validator.parseYCoordinates(args[2]);
            Validator.parseAnnualTurnover(args[3]);
        } catch (NumberFormatException | InvalidDataException e) {
            return false;
        }

        if (!args[4].equalsIgnoreCase("null")) {
            try {
                OrganizationType.valueOf(args[4].toUpperCase());
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        if (args[5].equalsIgnoreCase("null") &&
                args[6].equalsIgnoreCase("null") &&
                args[7].equalsIgnoreCase("null") &&
                args[8].equalsIgnoreCase("null")) {
            return true;
        }

        if (!args[5].equalsIgnoreCase("null") &&
                args[6].equalsIgnoreCase("null") &&
                args[7].equalsIgnoreCase("null") &&
                args[8].equalsIgnoreCase("null")) {
            try {
                Validator.validateStreetName(args[5]);
            } catch (InvalidDataException e) {
                return false;
            }
        }

        if (!args[5].equalsIgnoreCase("null") &&
                !args[6].equalsIgnoreCase("null") &&
                !args[7].equalsIgnoreCase("null") &&
                !args[8].equalsIgnoreCase("null")) {
            try {
                Validator.validateStreetName(args[5]);
                Validator.parseXLocation(args[6]);
                Validator.parseYLocation(args[7]);
                Validator.parseZLocation(args[8]);
            } catch (InvalidDataException e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void execute() throws InvalidDataException {
        try {
            String updatingKey = Main.console.getToken(1);
            int key = Validator.validateInt(updatingKey);

            InteractiveParser parser = new InteractiveParser();
            Organization organization = parser.parseOrganization();
            if (collectionManager.getCollection().containsKey(key)) {
                collectionManager.removeOrganizationByKey(key);
            }
            collectionManager.addOrganization(key, organization);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            int id = Validator.validateInt(args[0]);
            if (args.length != 9) {
                return;
            }

            String name = args[0].trim();
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
                xCoordinates = Validator.parseXCoordinates(args[1]);
            } catch (InvalidDataException e) {
                return;
            }

            try {
                yCoordinates = Validator.parseYCoordinates(args[2]);
            } catch (NumberFormatException e) {
                return;
            }

            try {
                annualTurnover = Validator.parseAnnualTurnover(args[3]);
            } catch (InvalidDataException e) {
                return;
            }

            String streetName;
            try {
                streetName = Validator.validateStreetName(args[5].trim());
            } catch (InvalidDataException e) {
                return;
            }

            try {
                xLocation = Validator.parseXLocation(args[6]);
            } catch (InvalidDataException e) {
                return;
            }

            try {
                yLocation = Validator.parseYLocation(args[7]);
            } catch (NumberFormatException e) {
                return;
            }

            try {
                zLocation = Validator.parseZLocation(args[8]);
            } catch (NumberFormatException e) {
                return;
            }

            OrganizationType type = null;
            if (!args[4].equalsIgnoreCase("null")) {
                try {
                    type = OrganizationType.valueOf(args[4].toUpperCase());
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

            collectionManager.addOrganization(id, organization);

        } catch (Exception ignored) {
        }
    }

}