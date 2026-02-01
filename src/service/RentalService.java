package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Car;
import repository.CarRepositoryPort;
import repository.RentalRepository;

import java.time.LocalDate;

public class RentalService {

    private final CarRepositoryPort carRepository;
    private final RentalRepository rentalRepository;

    public RentalService(CarRepositoryPort carRepository,
                         RentalRepository rentalRepository) {
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
    }

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
        carRepository.update(car);
    }
}
