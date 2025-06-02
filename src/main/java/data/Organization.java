package data;

import exceptions.InvalidDataException;
import utils.IDGenerator;
import utils.Validator;

import java.time.LocalDate;
import static utils.Validator.*;

//Реализовать интерфейс CompareTo

public class Organization implements Comparable<Organization> {
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization(String organizationName, Coordinates coordinates,
                        long inputAnnualTurnover, OrganizationType inputType, Address address) {
        this.id = IDGenerator.generateID();
        this.name = organizationName;
        this.creationDate = LocalDate.now();
        this.coordinates = coordinates;
        this.officialAddress = address;
        this.annualTurnover = inputAnnualTurnover;
        this.type = inputType;
    }

    public Organization() {
        this.id = IDGenerator.generateID();
        this.creationDate = LocalDate.now();
    }

    public int getID() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getName() { return name; }

    public Coordinates getCoordinates() { return coordinates; }

    public long getAnnualTurnover() { return annualTurnover; }

    public OrganizationType getType() { return type; }

    public Address getOfficialAddress() { return officialAddress; }

    public void setName(String orgName) { this.name = orgName.trim(); }

    public void setAnnualTurnover(String annualTurnover) throws InvalidDataException {
        this.annualTurnover = Validator.parseAnnualTurnover(annualTurnover);
    }

    public void setType(String orgType) throws InvalidDataException {
        this.type = Validator.parseOrganizationType(orgType);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setOfficialAddress(Address address) {
        this.officialAddress = address;
    }

    @Override
    public int compareTo(Organization other) {
        return Long.compare(this.annualTurnover, other.annualTurnover);
    }
}