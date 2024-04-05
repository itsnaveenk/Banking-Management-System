package main.webapp;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root")) {
                Statement statement = connection.createStatement();
                String query = "SELECT password FROM users WHERE email = '" + email + "'";
                ResultSet resultSet = statement.executeQuery(query);
                String query1 = "SELECT balance FROM users WHERE email = '" + email + "'";
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (password.equals(storedPassword)) {
                        // Successful login
                        HttpSession session = request.getSession();
                        session.setAttribute("email", email); // Save the email in the session
                        response.sendRedirect("dashboard.jsp");

                    } else {
                        // Incorrect password
                        request.setAttribute("errorMessage", "Incorrect password");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    // Email not registered
                    request.setAttribute("errorMessage", "Email not registered");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
