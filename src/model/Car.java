package model;

public class Car extends BaseEntity implements Validatable, PricedItem {

    private double pricePerDay;
    private boolean available;
    private Engine engine;

    public Car(int id, String name, double pricePerDay, Engine engine) {
        super(id, name);
        this.pricePerDay = pricePerDay;
        this.engine = engine;
        this.available = true;
        validate();
    }

    @Override
    public void validate() {
        if (pricePerDay <= 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
    }

    @Override
    public double calculatePrice(int days) {
        return pricePerDay * days;
    }

    @Override
    public String getEntityType() {
        return "Car";
    }

    @Override
    public String getDescription() {
        return getName() + " | " + engine.getInfo() + " | $" + pricePerDay + "/day";
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        this.available = false;
    }

    public Engine getEngine() {
        return engine;
    }
}
