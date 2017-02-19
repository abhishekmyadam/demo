package com.mycollege.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/reg")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	String username=request.getParameter("uname");
	String password=request.getParameter("pwd");
	String role=request.getParameter("role");
	
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			PreparedStatement pstmt=con.prepareStatement("insert into register values(?,?,?)");


			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, role);

			pstmt.executeUpdate();
			pstmt.close();
			
			
			RequestDispatcher dispatch=request.getRequestDispatcher("Login.jsp");
			dispatch.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
	}

}
