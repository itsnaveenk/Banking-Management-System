package main.webapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class test {

    private static DatabaseMetaData x;

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root");
        x = connection.getMetaData();
        String driverVersion = x.getDriverVersion();

        System.out.println("JDBC Driver Version: " + driverVersion);
    }
}
