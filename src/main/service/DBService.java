package main.service;
import java.sql.*;

public class DBService {
    private static DBService instance = new DBService();

    private Connection con;

    private static final String DB_URL = "jdbc:h2:./mocking";
    private static final String USER = "sa";
    private static final String PASS = "";

    private DBService() {
        try {
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Database Connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBService getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    public ResultSet get(PreparedStatement stm) {
        // execute a prepared statement on the database
        // return the ResultSet
        return null;
    }

    public void set(PreparedStatement stm) {
        // execute a prepared statement on the database
    }
}
