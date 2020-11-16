package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/sign_up")
public class RegistrationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");

        if (Objects.isNull(user)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "Post param \"userName\" required.",
                    "/sign_up.html"
            );
            return;
        }

        if (Objects.isNull(pass)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "Post param \"userPass\" required.",
                    "/sign_up.html"
            );
            return;
        }

        if (user.isEmpty()) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "User name should be not empty.",
                    "/sign_up.html"
            );
            return;
        }
        if (user.equals("admin")) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    String.format("You can not create %s user.", user),
                    "/sign_up.html"
            );
            return;
        }

        chain.doFilter(request, response);

    }

}
