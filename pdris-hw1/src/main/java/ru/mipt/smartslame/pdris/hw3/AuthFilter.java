package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/sign_in")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");

        if (Objects.isNull(user)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "Post param \"userName\" required.",
                    "/sign_in.html"
            );
            return;
        }

        if (Objects.isNull(pass)) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "Post param \"userPass\" required.",
                    "/sign_in.html"
            );
            return;
        }

        if (user.isEmpty()) {
            Utils.addLineAndRedirect(
                    request,
                    response,
                    "User name should be not empty.",
                    "/sign_in.html"
            );
            return;
        }

        chain.doFilter(request, response);
    }
}
