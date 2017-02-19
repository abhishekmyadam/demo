package com.SampleProject;

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
@WebServlet("/Register")
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
	
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	String email=request.getParameter("email");
	String retype=request.getParameter("pass");
	String gender=request.getParameter("gender");
	String course=request.getParameter("course");
	String address=request.getParameter("address");
	String role=request.getParameter("role");
	

	try {
		Class.forName("com.mysql.jdbc.Driver");
	
	
	try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhi","root","root");
	
	PreparedStatement pstmt=con.prepareStatement("insert into myportal values(?,?,?,?,?,?,?,?)");
	
	pstmt.setString(1,username);
	pstmt.setString(2,password);
	pstmt.setString(3,email);
    pstmt.setString(4,retype);
    pstmt.setString(5,gender);
    pstmt.setString(6,course);
    pstmt.setString(7,address);
    pstmt.setString(8,role);
	
	pstmt.executeUpdate();
	
	RequestDispatcher dispatch=request.getRequestDispatcher("Login.jsp");
	request.setAttribute("msg","user registered succesfully");
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
