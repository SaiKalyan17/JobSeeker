
package com.ntt.serv;
import javax.servlet.*;
import javax.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;

@WebServlet("/submitFeedback") // Map the servlet to this URL pattern
public class FeedbackServlet extends HttpServlet {
    
    // JDBC Details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/job_portal_db";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Chinni126";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String feedback = request.getParameter("feedback");

        try {
            // Save feedback to database
            saveFeedback(name, role, feedback);
            
            // Redirect to a success page after submission
            response.sendRedirect("feedbackSuccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("feedbackError.jsp");
        }
    }

    private void saveFeedback(String name, String role, String feedback) throws SQLException {
        // JDBC Connection and SQL Insert
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            String sql = "INSERT INTO feedback (name, role, feedback) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, role);
                stmt.setString(3, feedback);
                stmt.executeUpdate();
            }
        }
    }
}
