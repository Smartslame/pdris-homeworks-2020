package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/sign_in")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String user = request.getParameter("userName");

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
