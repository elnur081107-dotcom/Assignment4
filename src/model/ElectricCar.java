package model;

public class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(int id, String name, double pricePerDay,
                       Engine engine, int batteryCapacity) {
        super(id, name, pricePerDay, engine);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String getEntityType() {
        return "ElectricCar";
    }

    @Override
    public String getDescription() {
        return getName()
                + " | Electric " + batteryCapacity + " kWh"
                + " | " + getEngine().getInfo()
                + " | $" + calculatePrice(1) + "/day";
    }
}
