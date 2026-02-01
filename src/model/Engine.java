package model;

public class Engine {

    private final String type;
    private final int horsepower;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    public String getInfo() {
        return type + " " + horsepower + " HP";
    }
}
