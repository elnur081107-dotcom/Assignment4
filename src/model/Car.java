package model;

public class Car extends BaseEntity implements Validatable, PricedItem {

    protected double pricePerDay;
    protected boolean available;

    public Car(int id, String name, double pricePerDay) {
        super(id, name);
        this.pricePerDay = pricePerDay;
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
        return name + " $" + pricePerDay + "/day";
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        this.available = false;
    }

    public void returnCar() {
        this.available = true;
    }
}

