package appStudio.server.modelo;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    //private final String URL= "jdbc:sqlserver://localhost:1433;database=MVCDB;user=admin;password=admin;encrypt=false";
    private final String URL= "jdbc:sqlserver://localhost:1433;database=MVCDB;user=sa;password=admin12345$;encrypt=false";
    private final String USER="admin";
    private final String PASSWORD="admin";
    private Connection connection;


    public Connection connect (){
        connection =null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();} catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void close (){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
