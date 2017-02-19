package com.SampleProject;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.mysql.jdbc.Connection;

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

	String username=request.getParameter("username");
	//String email=request.getParameter("email");//
	String password=request.getParameter("password");
	//String role=request.getParameter("role");//
	String role="";
	boolean isvaliduser=false;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		try{
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Abhi","root","root");
			PreparedStatement pstmt1=con.prepareStatement("select * from myportal where username=? and password=?");
		
		pstmt1.setString(1,username);
	
		pstmt1.setString(2,password);
	
		ResultSet rs=pstmt1.executeQuery();

		
		while(rs.next()){
			System.out.println("this is"+ rs.getString(8));
		//	out.write(rs.getString(1));//
			//out.write(rs.getString(2));//
			role=rs.getString(8);
		//	out.write(rs.getString(4));//
			isvaliduser=true;
		}
		
		if(isvaliduser==true && role.equalsIgnoreCase("Professor")){
			RequestDispatcher dispatch1=request.getRequestDispatcher("Professor.jsp");
			dispatch1.forward(request,response);
		}
		else if(isvaliduser==true && role.equalsIgnoreCase("Student")){
			RequestDispatcher dispatch2=request.getRequestDispatcher("Student.jsp");
			dispatch2.forward(request,response);
		}else {
			RequestDispatcher dispatch3=request.getRequestDispatcher("Login.jsp");
		dispatch3.forward(request,response);
		}
		
		
		
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	
	
	
	}

}
