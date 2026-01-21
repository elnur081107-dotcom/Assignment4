package model;

public class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(int id, String name, double pricePerDay, int batteryCapacity) {
        super(id, name, pricePerDay);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String getEntityType() {
        return "ElectricCar";
    }

    @Override
    public String getDescription() {
        return name + " (Electric, " + batteryCapacity + " kWh)";
    }
}

