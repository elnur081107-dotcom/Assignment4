package model;

public class Customer extends BaseEntity {

    private String email;

    public Customer(int id, String name, String email) {
        super(id, name);
        setEmail(email);
    }

    @Override
    public String getEntityType() {
        return "Customer";
    }

    @Override
    public String getDescription() {
        return name + " (" + email + ")";
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }
}

