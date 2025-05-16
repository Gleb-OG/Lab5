package data;

import utils.Validator;


public class Coordinates {
    private Double x; //Значение поля должно быть больше -922, Поле не может быть null
    private long y;

    /**
     * Задает координаты объекта класса Coordinates из двух элементов типа String,
     * соответствующих координатам "х" и "y".*/
    public Coordinates(String inputX, String inputY) {
        setX(inputX);
        setY(inputY);
    }

    public void setX(String input) {
        this.x = Validator.parseXCoordinates(input);
    }

    public void setY(String input) {
        this.y = Validator.parseYCoordinates(input);
    }

    public Double getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }
}