import utils.DatabaseConnection;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try (Connection c = DatabaseConnection.getConnection()) {
            System.out.println("CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



