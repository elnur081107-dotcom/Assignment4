package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Car;
import repository.CarRepository;
import repository.RentalRepository;

import java.time.LocalDate;

public class RentalService {

    private final CarRepository carRepository = new CarRepository();
    private final RentalRepository rentalRepository = new RentalRepository();

    public void rentCar(int carId, int customerId,
                        LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new InvalidInputException("Start date must be before end date");
        }

        Car car = carRepository.getById(carId);
        if (car == null) {
            throw new ResourceNotFoundException("Car not found");
        }

        if (!car.isAvailable()) {
            throw new InvalidInputException("Car is not available");
        }

        rentalRepository.create(carId, customerId, start, end);
        car.rent();
    }
}

