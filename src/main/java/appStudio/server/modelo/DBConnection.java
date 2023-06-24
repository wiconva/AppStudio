package appStudio.server.modelo;

import appStudio.server.utilities.ConfigFileReader;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private String connectionString = null;

   // private final String URL= "jdbc:sqlserver://localhost:1433;database=MVCDB;user=sa;password=admin12345$;encrypt=false";
    private Connection connection;


    public Connection connect (){
        connection =null;
        ConfigFileReader connectionConfigFileReader = new ConfigFileReader();
        connectionString = connectionConfigFileReader.getKeyValue("Server.ConnectionConfig.txt","ConnectionString");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();} catch (Exception e) {
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
