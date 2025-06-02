package data;

import exceptions.InvalidDataException;
import utils.Validator;

public class Address {
    private String street; //Длина строки не должна быть больше 103, Поле не может быть null
    private Location town; //Поле может быть null

    public Address(String inputStreetName, Location inputTown) {
        this.street = inputStreetName;
        this.town = inputTown;
    }
    public Address(String inputStreetName) {
        this.street = inputStreetName;
        this.town = null;
    }

    public String getStreet() {
        return street;
    }

    public Location getTown() {
        return town;
    }

    public void setStreet(String inputStreetName) throws InvalidDataException {
        this.street = Validator.validateStreetName(inputStreetName);
    }

    public void setLocation(Location location) {
        this.town = location;
    }
}
