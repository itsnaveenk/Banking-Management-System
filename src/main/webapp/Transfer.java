package main.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String senderEmail = (String) session.getAttribute("email");
        String receiverEmail = req.getParameter("receiverEmail");
        Double amount = Double.parseDouble(req.getParameter("amount"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rana_bank", "root", "root")) {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT balance FROM users WHERE email = '" + senderEmail + "'");
                if (resultSet.next()) {
                    double senderBalance = resultSet.getDouble("balance");
                    if (senderBalance < amount) {
                        req.setAttribute("errormessage", "Insufficient balance");
                        req.getRequestDispatcher("transfer.jsp").forward(req, resp);
                        return;
                    }

                    resultSet = statement.executeQuery("select balance from users where email = '" + receiverEmail + "'");
                    if (resultSet.next()) {
                        double receiverBalance = resultSet.getDouble("balance");
                        statement.executeUpdate("UPDATE users SET balance = " + (senderBalance - amount) + " WHERE email = '" + senderEmail + "'");
                        statement.executeUpdate("UPDATE users SET balance = " + (receiverBalance + amount) + " WHERE email = '" + receiverEmail + "'");

                        req.setAttribute("successMessage", "Transfer Successful");
                        req.getRequestDispatcher("transfer.jsp").forward(req, resp);

                    } else {
                        req.setAttribute("errorMessage", "Receiver does not exist.");
                        req.getRequestDispatcher("transfer,jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorMessage", "Sender does not exist");
                    req.getRequestDispatcher("transfer.jsp").forward(req, resp);
                }


            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
}
