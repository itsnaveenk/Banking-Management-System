package main.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/checkBalance")
public class CheckBalance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root")) {
                Statement statement = connection.createStatement();
                String query = "SELECT balance FROM users WHERE email = '" + email + "'";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String balance = resultSet.getString("balance");
                    req.setAttribute("balance", balance);
                } else {
                    req.setAttribute("errorMessage", "Error while fetching balance");
                }
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
