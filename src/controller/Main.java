package controller;

import model.Car;
import model.ElectricCar;
import service.CarService;
import service.RentalService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        CarService service = new CarService();
        RentalService rentalService = new RentalService();

        Car car1 = new Car(0, "BMW X5", 90);
        Car car2 = new ElectricCar(0, "Tesla Model Y", 130, 80);

        service.addCar(car1);
        service.addCar(car2);

        System.out.println("=== ALL CARS ===");
        service.getAllCars().forEach(c ->
                System.out.println(c.getDescription())
        );

        rentalService.rentCar(
                2,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(5)
        );

        System.out.println("=== DELETE CAR ID 1 ===");
        service.deleteCar(1);

        System.out.println("=== DONE ===");
    }
}




