
package com.employee.controller;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.sql.DataSource;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null ||
                username.isBlank() || password.isBlank()) {

            request.setAttribute(
                    "error", "Please enter username and password.");

            request.getRequestDispatcher("index.html")
                    .forward(request, response);
            return;
        }

        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext());

        DataSource ds = context.getBean(DataSource.class);

        String sql = "SELECT Password FROM login WHERE Email = ?";

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String storedPassword = rs.getString("Password");

                    if (password.equals(storedPassword)) {

                        HttpSession session =
                                request.getSession(true);

                        session.setAttribute("username", username);
                        session.setAttribute("role", "ADMIN");
                        session.setMaxInactiveInterval(900);

                        response.sendRedirect("dashboard.jsp");
                        return;
                    }
                }
            }

            request.setAttribute(
                    "error", "Invalid username or password.");

            request.getRequestDispatcher("index.html")
                    .forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Login database error", e);
        }
    }
}
