package data;

import utils.IDGenerator;
import java.time.LocalDate;
import static utils.Validator.*;

//Реализовать интерфейс CompareTo

public class Organization {
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization(String inputName, Coordinates coordinates,
                        String inputAnnualTurnover, String inputType, Address address) {
        this.id = IDGenerator.generateID();
        this.creationDate = LocalDate.now();
        this.coordinates = coordinates;
        this.officialAddress = address;
        setName(inputName);
        setAnnualTurnover(inputAnnualTurnover);
        setType(inputType);
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

    public void setName(String input) {
        validateOrganizationName(input);
        this.name = input;
    }

    public void setAnnualTurnover(String input) {
        this.annualTurnover = parseAnnualTurnover(input);
    }

    public void setType(String input) {
        this.type = parseOrganizationType(input);
    }
}