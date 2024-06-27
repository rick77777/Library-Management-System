/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Library.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHIRADIP
 */
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    List<String> errorMsgs=new ArrayList<String>();
    //JDBC variables
		 
        PrintWriter out = response.getWriter();
	Connection con = null;  
	PreparedStatement pstmt = null;
	Statement statement=null;
		
	//send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
		
	try  {
		//Retrieve form parameters
		String firstName=request.getParameter("firstname").trim();
		String surname=request.getParameter("surname").trim();
		String age=request.getParameter("age").trim();
		String gender=request.getParameter("gender").trim();
                String email=request.getParameter("email").trim();
		String userName=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
			
		//Perform data conversions
			
		int ageUser=-1;
			
		try  {
		ageUser=Integer.parseInt(age);
		}
		catch(NumberFormatException nfe) {
		errorMsgs.add("The age must be a positive integer");
		}
			
		//Verify form parameters
			
		if(firstName.length()==0)  {
		errorMsgs.add("Please enter the first name");
		}
		if(surname.length()==0)  {
		errorMsgs.add("Please enter the surname");
		}
		if(age.length()==0)  {
		errorMsgs.add("Please enter the age");
		}
		if(userName.length()==0)  {
		errorMsgs.add("Please enter the username");
		}
		if(password.length()==0)  {
		errorMsgs.add("Please enter the password");
		}
			
		//Perform business logic
		User user =new User(firstName,surname,ageUser,gender,email,userName,password);
			
		//Store the new user in the request-scope
		request.setAttribute("user", user);
                
		//Send the ErrorPage view if there were errors
		if(!errorMsgs.isEmpty())  {
		RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
		view.forward(request, response);
		return;
		}
                
		//Store the new user into the database
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con =DriverManager.getConnection 
		("jdbc:mysql://127.0.0.1:3306/library","root","");
		statement=con.createStatement();
		pstmt = con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
		pstmt.setString(1,firstName);
		pstmt.setString(2,surname);
		pstmt.setString(3,age);
		pstmt.setString(4,gender);
                pstmt.setString(5,email);
                pstmt.setString(6,userName);
		pstmt.setString(7,password);
		pstmt.executeUpdate();	
				  	
		//Send the success view
		RequestDispatcher view = request.getRequestDispatcher("successUser.jsp");
		view.forward(request, response);
		return;
					
				}
		catch(SQLException e) {
		errorMsgs.add(e.getMessage());
			//dispatch to the ErrorPage
                 RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
		e.printStackTrace();
		view.forward(request, response);					 }
	        catch(Exception e){
	        e.printStackTrace();
		}
			}

		//Handle any unexpected exceptions
		catch(RuntimeException e)  {
			errorMsgs.add(e.getMessage());
			//dispatch to the ErrorPage
			RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
			view.forward(request, response);
	                }//END of try-catch block
	        }//END of doPost method
            }//END of AddUserServlet class