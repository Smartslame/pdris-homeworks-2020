package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/sign_in")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");

        ServletContext servletContext = request.getServletContext();

        Map<String, String> users = Utils.getUsers(servletContext);


        if (!users.containsKey(user)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    String.format("User %s doesn't exist. Please, sign up to proceed.", user),
                    "/sign_in.html"
            );
            return;
        }


        if (pass.equals(users.get(user))) {
            request.setAttribute("name", user);
            RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
            rd.include(request, response);

        } else {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "Password is incorrect",
                    "/sign_in.html"
            );
        }

    }

}