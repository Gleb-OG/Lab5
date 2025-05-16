package data;

import static utils.Validator.*;

public class Location {
    private float x;
    private double y;
    private Long z; //Поле не может быть null

    public Location(String inputX, String inputY, String inputZ) {
        setX(inputX);
        setY(inputY);
        setZ(inputZ);
    }

    public float getX() { return x; }

    public double getY() { return y; }

    public Long getZ() { return z; }

    public void setX(String input) {
        this.x = parseXLocation(input);
    }

    public void setY(String input) {
        this.y = parseYLocation(input);
    }

    public void setZ(String input) {
        this.z = parseZLocation(input);
    }
}