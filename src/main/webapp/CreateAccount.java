package main.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateAccount {
    private final String name;
    private final String email;
    private final String password;
    private final String deposit;

    public CreateAccount(String name, String email, String password, String deposit) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.deposit = deposit;
    }

    public boolean createAccount() {
        boolean flag = false;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root");
            connection.createStatement().execute("INSERT INTO users (name, email, password, balance) VALUES ('" + name + "', '" + email + "', '" + password + "', " + deposit + ")");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return flag;
    }
}
