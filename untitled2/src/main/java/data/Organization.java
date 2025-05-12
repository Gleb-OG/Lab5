package data;

import utils.IDGenerator;

import java.time.LocalDate;

public class Organization {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization() {
        this.id = IDGenerator.generateID();
        this.creationDate = LocalDate.now();
    }

    public int getID() {
        return this.id;
    }

    public java.time.LocalDate getCreationDate() {
        return this.creationDate;
    }

}