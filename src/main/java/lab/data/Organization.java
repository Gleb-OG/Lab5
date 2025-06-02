package lab.data;

import lab.exceptions.InvalidDataException;
import lab.utils.IDGenerator;
import lab.utils.Validator;

import java.time.LocalDate;

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

    public void setAnnualTurnover(long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setType(OrganizationType orgType) {
        this.type = orgType;
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

    @Override
    public String toString() {
        return "Organization\n" +
                "id = " + id +
                "\nname = " + name +
                "\ncreationDate = " + creationDate +
                "\ncoordinates = " + coordinates +
                "\nannual turnover = " + annualTurnover +
                "\norganization type = " + type +
                "\naddress = " + officialAddress +
                "\n-------------------------";
    }
}