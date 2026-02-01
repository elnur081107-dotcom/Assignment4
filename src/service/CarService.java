package service;

import exception.ResourceNotFoundException;
import model.Car;
import repository.CarRepositoryPort;

import java.util.List;

public class CarService {

    private final CarRepositoryPort repository;

    public CarService(CarRepositoryPort repository) {
        this.repository = repository;
    }

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

    public void updateCar(Car car) {
        getCar(car.getId());
        car.validate();
        repository.update(car);
    }

    public void deleteCar(int id) {
        getCar(id);
        repository.delete(id);
    }
}
