package com.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
	String fname=request.getParameter("fname");
	try{
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhi","root","root");
			java.sql.PreparedStatement pstmt=con.prepareStatement("update myportal set username=?,password=? where username=?");
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,fname);
			pstmt.executeUpdate();
			System.out.println("This is update");
			System.out.println("username");
			System.out.println("password");
			System.out.println("fname");
			RequestDispatcher dispatch=request.getRequestDispatcher("Students.jsp");
			dispatch.forward(request,response);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	
	
	
	
	
	
	
	
	
	}

}
