package repository;

import model.Car;
import java.util.List;

public interface CarRepositoryPort {
    void create(Car car);
    Car getById(int id);
    List<Car> getAll();
    void update(Car car);
    void delete(int id);
}


