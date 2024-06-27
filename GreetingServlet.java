package Library;

import Library.model.Book;
import Library.model.CheckOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class GreetingServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        //out.println("Hello from POST Method " + username);
        
        if(username.equals("admin") && password.equals("admin")) {
         RequestDispatcher view = request.getRequestDispatcher("adminsection.jsp");
                           view.forward(request, response);
                           return;
         		        	
    }else{
        List<String> errorMsgs=new ArrayList<String>();
        Connection con = null;  
        PreparedStatement stmt = null;
        ResultSet rs = null;
            //send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con =DriverManager.getConnection 
             ("jdbc:mysql://127.0.0.1:3306/library","root","");
             
             stmt = con.prepareStatement("select * from Users where username = ? and password = ?");
             stmt.setString(1, username);
             stmt.setString(2, password);
             rs = stmt.executeQuery();
             String id = null;

             if(rs.next()){
                   id= rs.getString(1);

             }else{
                     errorMsgs.add("Invalid username or password");
                     //return;
             }
           //Send the ErrorPage view if there were errors
             if(!errorMsgs.isEmpty())  {
                           RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                           view.forward(request, response);
                           return;
                   }      


            stmt = con.prepareStatement("select * from Checkout where username = ? order by return_date");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            List<CheckOut> checkedOutItems = new ArrayList<CheckOut>();
            while(rs.next()){
                    CheckOut item = new CheckOut();
                    item.setTransactionId(rs.getInt(1));
                    item.setBookId(rs.getInt(2));	  
                    item.setUserName(rs.getString(3));
                    item.setReturnDate(rs.getDate(4));
                    checkedOutItems.add(item);
            }
            request.setAttribute("checkedOutItems", checkedOutItems);
            
            
            Statement statement = null;
            ResultSet rs2 = null;
            statement = con.createStatement();
                        rs2 = statement.executeQuery("SELECT * FROM Books");

                        List<Book> books = new ArrayList<Book>();
                        while(rs2.next()){
                            Book book = new Book();
                                        book.setBookId(rs2.getInt("id"));
                                        book.setBookName(rs2.getString("bookName"));
                                        book.setAuthorName(rs2.getString("authorName"));
                                        book.setISBN(rs2.getString("ISBN"));
                                        book.setPublisher(rs2.getString("publisher"));
                                        book.setYear(rs2.getInt("year_of_publication"));
                                        book.setSubject(rs2.getString("subject"));
                                        book.setTotalCopies(rs2.getInt("totalcopies"));
                                        book.setAvailCopies(rs2.getInt("availablecopies"));
                                        books.add(book);
                        }
            request.setAttribute("books", books);
            
           //Send the success view
            RequestDispatcher view = request.getRequestDispatcher("successGreeting.jsp");
            view.forward(request, response);
            return;
        }

       catch (SQLException e) {
        throw new ServletException("Servlet Could not display records.", e);
         } catch (ClassNotFoundException e) {
         throw new ServletException("JDBC Driver not found.", e);
         } finally {
         try {
         if(rs != null) {
         rs.close();
         rs = null;
         }
         if(stmt != null) {
         stmt.close();
         stmt = null;
         }
         if(con != null) {
         con.close();
         con = null;
         }
         } catch (SQLException e) {}
         }
        }
    }
}