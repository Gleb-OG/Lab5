package data;

import exceptions.InvalidDataException;

import static utils.Validator.*;

public class Location {
    private float x;
    private double y;
    private Long z; //Поле не может быть null

    public Location(float inputX, double inputY, Long inputZ) {
        this.x = inputX;
        this.y = inputY;
        this.z = inputZ;
    }

    public float getX() { return x; }

    public double getY() { return y; }

    public Long getZ() { return z; }

    public void setX(String input) throws InvalidDataException {
        this.x = parseXLocation(input);
    }

    public void setY(String input) throws InvalidDataException {
        this.y = parseYLocation(input);
    }

    public void setZ(String input) throws InvalidDataException {
        this.z = parseZLocation(input);
    }

    public String fileToString() {
        return x + "," + y + "," + z;
    }

    @Override
    public String toString() {
        return " {" +
                "\n      x = " + x +
                "\n      y = " + y +
                "\n      z = " + z + "\n}";
    }
}