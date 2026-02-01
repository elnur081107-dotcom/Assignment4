package utils;

import model.Car;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static List<Car> sortByPrice(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparingDouble(c -> c.calculatePrice(1)))
                .toList();
    }
}

