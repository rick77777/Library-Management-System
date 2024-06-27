package Library;

import Library.model.Book;
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
public class NewBook extends HttpServlet {

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
		String id=request.getParameter("id").trim();
		String bkname=request.getParameter("bkname").trim();
		String aname=request.getParameter("aname").trim();
		String isbn=request.getParameter("isbn").trim();
                String pub=request.getParameter("pub").trim();
                String sub=request.getParameter("sub").trim();
                String year=request.getParameter("year").trim();
		String totcopy=request.getParameter("totcopy").trim();
			
		//Perform data conversions
			
		int idBk=-1,yr=0;

		idBk=Integer.parseInt(id);
                yr = Integer.parseInt(year);
		if(idBk<1)
		errorMsgs.add("The Book ID must be a positive integer");
                if(yr<1900 || yr>2020)
		errorMsgs.add("The Year entered is Invalid !!");
                int tot=-1;
			
		try  {
		tot=Integer.parseInt(totcopy);
		}
		catch(NumberFormatException nfe) {
		errorMsgs.add("The Total number of copies must be a positive integer");
		}
			
		//Verify form parameters
			
		if(bkname.length()==0)  {
		errorMsgs.add("Please enter the book name");
		}
		if(aname.length()==0)  {
		errorMsgs.add("Please enter the author name");
		}
		if(id.length()==0)  {
		errorMsgs.add("Please enter the book ID");
		}
		if(isbn.length()==0)  {
		errorMsgs.add("Please enter the ISBN");
		}
		if(pub.length()==0)  {
		errorMsgs.add("Please enter the publisher");
		}
                if(sub.length()==0)  {
		errorMsgs.add("Please enter the subject");
		}
                if(year.length()==0)  {
		errorMsgs.add("Please enter the year");
		}
                if(totcopy.length()==0)  {
		errorMsgs.add("Please enter the total copies");
		}
			
		//Perform business logic
		Book book = new Book(idBk,bkname,aname,isbn,pub,sub,yr,tot,tot);	
		//Store the new book in the request-scope
		request.setAttribute("book", book);
                
		//Send the ErrorPage view if there were errors
		if(!errorMsgs.isEmpty())  {
		RequestDispatcher view = request.getRequestDispatcher("addBook.jsp");
		view.forward(request, response);
		return;
		}
                
		//Store the new book into the database
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con =DriverManager.getConnection 
		("jdbc:mysql://127.0.0.1:3306/library","root","");
		statement=con.createStatement();
		pstmt = con.prepareStatement("insert into books values(?,?,?,?,?,?,?,?,?)");
		pstmt.setString(1,id);
		pstmt.setString(2,bkname);
		pstmt.setString(3,aname);
		pstmt.setString(4,isbn);
                pstmt.setString(5,pub);
                pstmt.setString(6,sub);
                pstmt.setString(7,year);
                pstmt.setString(8,totcopy);
		pstmt.setString(9,totcopy);
		pstmt.executeUpdate();	
				  	
		//Send the success view
		RequestDispatcher view = request.getRequestDispatcher("successBook.jsp");
		view.forward(request, response);
		return;
					
				}
		catch(SQLException e) {
		errorMsgs.add(e.getMessage());
			//dispatch to the ErrorPage
                 RequestDispatcher view = request.getRequestDispatcher("addBook.jsp");
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
			RequestDispatcher view = request.getRequestDispatcher("addBook.jsp");
			view.forward(request, response);
	                }//END of try-catch block
	        }//END of doPost method
            }
                            /**
                            int i=statement.executeUpdate("insert into users(first_name,last_name,city_name,email)values('"+first_name+"','"+last_name+"','"+city_name+"','"+email+"')");
                            out.println("Data is successfully inserted!");                           
                            **/