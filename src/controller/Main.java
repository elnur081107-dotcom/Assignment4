package controller;

import model.Car;
import model.ElectricCar;
import model.Engine;
import repository.CarRepository;
import repository.RentalRepository;
import service.CarService;
import service.RentalService;
import utils.ReflectionUtils;
import utils.SortingUtils;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // === INIT LAYERS  ===
        CarRepository carRepository = new CarRepository();
        RentalRepository rentalRepository = new RentalRepository();

        CarService carService = new CarService(carRepository);
        RentalService rentalService = new RentalService(carRepository, rentalRepository);

        // === CREATE  ===
        System.out.println("=== CREATE CARS ===");

        Engine petrolEngine = new Engine("Petrol", 250);
        Engine electricEngine = new Engine("Electric", 300);

        Car car1 = new Car(0, "BMW X5", 90, petrolEngine);
        Car car2 = new ElectricCar(0, "Tesla Model Y", 130, electricEngine, 80);

        carService.addCar(car1);
        carService.addCar(car2);

        // === READ ALL  ===
        System.out.println("\n=== ALL CARS ===");
        List<Car> cars = carService.getAllCars();
        cars.forEach(car ->
                System.out.println(car.getDescription())
        );

        // === SORTING  ===
        System.out.println("\n=== SORTED BY PRICE ===");
        SortingUtils.sortByPrice(cars)
                .forEach(car ->
                        System.out.println(car.getDescription())
                );

        // === READ BY ID ===
        System.out.println("\n=== GET CAR BY ID ===");
        try {
            Car foundCar = carService.getCar(1);
            System.out.println(foundCar.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // === BUSINESS LOGIC ===
        System.out.println("\n=== RENT CAR ===");
        try {
            rentalService.rentCar(
                    2,
                    1,
                    LocalDate.now(),
                    LocalDate.now().plusDays(5)
            );
            System.out.println("Car rented successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // === UPDATE ===
        System.out.println("\n=== UPDATE CAR ===");
        try {
            Car carToUpdate = carService.getCar(2);
            carToUpdate.rent();
            carService.updateCar(carToUpdate);
            System.out.println("Car updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // === DELETE ===
        System.out.println("\n=== DELETE CAR ===");
        try {
            carService.deleteCar(1);
            System.out.println("Car deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // === REFLECTION ===
        System.out.println("\n=== REFLECTION ===");
        ReflectionUtils.printClassInfo(Car.class);

        System.out.println("\n=== DONE ===");
    }
}
