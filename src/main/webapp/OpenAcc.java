package main.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/accountOpen")

public class OpenAcc extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./resources/acc_open.css\">"
                + "</head>");
        out.println("<body>");
        out.println("<section id=\"open-account\">");
        out.println("<h1>Open Account</h1>");
        out.println("<form action=\"./accountOpen\" method=\"post\">");
        out.println("<label for=\"name\">Name:</label>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" required>");
        out.println("<label for=\"email\">Email:</label>");
        out.println("<input type=\"email\" id=\"email\" name=\"email\" required>");
        out.println("<label for=\"password\">Password:</label>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\" required>");
        out.println("<label for=\"deposit\">Initial Deposit:</label>");
        out.println("<input type=\"number\" id=\"deposit\" name=\"deposit\" required>");
        out.println("<input type=\"submit\" value=\"Open Account\">");
        out.println("</form>");
        out.println("</section>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String deposit = req.getParameter("deposit");

        // Here you can add the code to handle the new account creation using the form data

        CreateAccount user = new CreateAccount(name, email, password, deposit);
        int flag = user.createAccount();

        if (flag==1) {
            out.println("<html><body>");
            out.println("<h1>Account Created Successfully</h1>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Account Creation Failed</h1>");
            out.println("</body></html>");

        }
    }
}
