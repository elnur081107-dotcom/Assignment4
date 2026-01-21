package model;

import java.time.LocalDate;

public class Rental {

    private int id;
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(int id, Car car, Customer customer,
                  LocalDate startDate, LocalDate endDate) {

        if (car == null || customer == null) {
            throw new IllegalArgumentException("Car and Customer required");
        }

        this.id = id;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

