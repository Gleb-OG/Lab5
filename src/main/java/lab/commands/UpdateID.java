package lab.commands;

import lab.Main;
import lab.data.*;
import lab.exceptions.InvalidDataException;
import lab.utils.IDGenerator;
import lab.utils.Validator;

public class UpdateID extends Command {

    public UpdateID() {
        super("update", "Обновление значения элемента коллекции по его id.", 1);
    }

    @Override
    public int getArgsAmount() {
        return Main.scriptMode ? 9 : 1;
    }

    @Override
    public boolean check(String[] args) {
        if (args.length != 7) return false;
        if (!args[0].matches("^\\d+$")) return false;

        int id = Integer.parseInt(args[0]);
        return collectionManager.getOrganizationByID(id) != null;
    }

    @Override
    public void execute() {
        try {
            String updatingID = Main.console.getToken(1);
            if (!updatingID.matches("^\\d+$")) {
                throw new InvalidDataException("id может быть только больше нуля.");
            }
            int id = Integer.parseInt(updatingID);
            collectionManager.updateID(id);
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
            Organization existOrg = collectionManager.getOrganizationByID(id);
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

            IDGenerator.releaseID(id);

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

            collectionManager.removeOrganizationByID(id);
            collectionManager.addOrganization(id, organization);
            System.out.println("Организация с id " + id + " обновлена успешно.");
        } catch (Exception ignored) {
        }
    }
}
