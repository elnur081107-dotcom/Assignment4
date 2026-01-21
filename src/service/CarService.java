package service;

import exception.ResourceNotFoundException;
import model.Car;
import repository.CarRepository;

import java.util.List;

public class CarService {

    private final CarRepository repository = new CarRepository();

    public void addCar(Car car) {
        car.validate();
        repository.create(car);
    }

    public List<Car> getAllCars() {
        return repository.getAll();
    }

    public Car getCar(int id) {
        Car car = repository.getById(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not found");
        }
        return car;
    }

    public void deleteCar(int id) {
        getCar(id);
        repository.delete(id);
    }
}

