import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/college_events";

    private static final String USER = "root";

    // If your MySQL has no password, keep this empty.
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database connected successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return con;
    }
}