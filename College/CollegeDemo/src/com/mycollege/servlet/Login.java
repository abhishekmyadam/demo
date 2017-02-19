package com.mycollege.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		boolean isvaliduser=false;
		String role="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				PreparedStatement pstmt=con.prepareStatement("select * from register where uname=? and pwd=?");

               
				pstmt.setString(1, uname);
				pstmt.setString(2, pwd);
		

				ResultSet rs=pstmt.executeQuery();
				
			
				
				while(rs.next()){
				System.out.println("------------"+rs.getString(3));
					role=rs.getString(3);
					isvaliduser=true;
				}
				
				if(isvaliduser==true && role.equalsIgnoreCase("Professor")){
					RequestDispatcher dispatcher =request.getRequestDispatcher("WelcomeProfessor.jsp");
					dispatcher.forward(request, response);
				}

				else if(isvaliduser==true && role.equalsIgnoreCase("Student")){
					RequestDispatcher dispatcher =request.getRequestDispatcher("WelcomeStudent.jsp");
					dispatcher.forward(request, response);
				}
				else{
					RequestDispatcher dispatcher =request.getRequestDispatcher("Login.jsp");
					dispatcher.forward(request, response);
				}
			
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
