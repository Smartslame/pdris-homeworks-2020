package ru.mipt.smartslame.pdris.hw3;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utils {
    private static void initializeUsers(ServletContext servletContext) {
        Map<String, String> users = new HashMap<>();
        users.put("admin", "admin");
        servletContext.setAttribute("users", users);

    }

    public static Map<String, String> getUsers(ServletContext servletContext) {
        if (Objects.isNull(servletContext.getAttribute("users"))) {
            Utils.initializeUsers(servletContext);
        }
        return (Map<String, String>) servletContext.getAttribute("users");
    }

    public static void addLineAndRedirect(ServletRequest request, ServletResponse response, String text, String redirect) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter write = response.getWriter();
        write.print(text);
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.include(request, response);
    }
}
