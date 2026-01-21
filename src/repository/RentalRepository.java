package repository;

import model.Rental;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

    public void create(int carId, int customerId,
                       LocalDate start, LocalDate end) {

        String sql = """
                INSERT INTO rentals (car_id, customer_id, start_date, end_date)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, carId);
            ps.setInt(2, customerId);
            ps.setDate(3, Date.valueOf(start));
            ps.setDate(4, Date.valueOf(end));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create rental", e);
        }
    }

    public List<Rental> getAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("id"),
                        null,
                        null,
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch rentals", e);
        }
        return rentals;
    }

    public void delete(int id) {
        String sql = "DELETE FROM rentals WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete rental", e);
        }
    }
}

