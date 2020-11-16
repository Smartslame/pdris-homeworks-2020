package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/sign_up")
public class RegistrationServlet extends HttpServlet {
    private Map<String, String> users;

    @Override
    public void init() throws ServletException {
        this.users = Utils.getUsers(getServletContext());
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");

        if (users.containsKey(user)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    String.format("User %s already exists.", user),
                    "/sign_up.html"
            );
            return;
        }

        users.put(user, pass);

        Utils.addLineAndRedirect(
                request,
                response,
                String.format("User %s created.", user),
                "/sign_in.html"
        );


    }
}
