package main.webapp;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        double depositAmount = Double.parseDouble(req.getParameter("amount"));


        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root")) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT balance from users where email = '" + email + "';");

                if (resultSet.next()) {
                    double balance = Double.parseDouble(resultSet.getString("balance"));

                    statement.executeUpdate("UPDATE users SET balance = " + (balance + depositAmount) + " WHERE email = '" + email + "';");
                    req.setAttribute("successMessage", "Deposit successful");
                    req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "User does not exist");
                    req.getRequestDispatcher("deposit.jsp").forward(req, resp);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
