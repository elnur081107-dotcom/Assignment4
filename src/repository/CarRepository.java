package repository;

import model.Car;
import model.Engine;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements CarRepositoryPort {

    @Override
    public void create(Car car) {
        String sql = """
                INSERT INTO cars (name, price_per_day, available)
                VALUES (?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, car.getName());
            ps.setDouble(2, car.calculatePrice(1));
            ps.setBoolean(3, car.isAvailable());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Engine engine = new Engine("Unknown", 1);

                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price_per_day"),
                        engine
                );

                if (!rs.getBoolean("available")) {
                    car.rent();
                }

                cars.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    @Override
    public Car getById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Engine engine = new Engine("Unknown", 1);

                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price_per_day"),
                        engine
                );

                if (!rs.getBoolean("available")) {
                    car.rent();
                }

                return car;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void update(Car car) {
        String sql = """
                UPDATE cars
                SET name = ?, price_per_day = ?, available = ?
                WHERE id = ?
                """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, car.getName());
            ps.setDouble(2, car.calculatePrice(1));
            ps.setBoolean(3, car.isAvailable());
            ps.setInt(4, car.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update car", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
