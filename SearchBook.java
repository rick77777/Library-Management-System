package Library;

import Library.model.Book;
import Library.model.CheckOut;
import Library.model.User;
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


public class SearchBook extends HttpServlet {

    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    List<String> errorMsgs=new ArrayList<String>();
    //JDBC variables
		 
        PrintWriter out = response.getWriter();  
	PreparedStatement pstmt = null;
        
        //send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
	
	//Retrieve form parameters
	String querySt = request.getParameter("query").trim();
        String menuSelection = request.getParameter("menuselection");	
		
        //Verify form parameters
        
			
	if(querySt.length()==0)  {
		errorMsgs.add("Please enter a detail to search");
	}
        if(menuSelection==null){
            errorMsgs.add("Please select an option");
        }
        //Send the ErrorPage view if there were errors
	if(!errorMsgs.isEmpty())  {
		RequestDispatcher view = request.getRequestDispatcher("searchBook.jsp");
		view.forward(request, response);
		return;
        }
        else{
		
                if(menuSelection.equals("bookname")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                          Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            PreparedStatement pst = null;
                            pst = connection.prepareStatement("select * from Books where bookname=?");
                            pst.setString(1, querySt);
                            rs = pst.executeQuery();
                            
                            PreparedStatement pst2 = null;
                            pst2 = connection.prepareStatement("select count(*) from Books where bookname=?");
                            pst2.setString(1, querySt);
                            rs2 = pst2.executeQuery();
                            
                            String Countrow="";
                            while(rs2.next())
                            {
                                Countrow = rs2.getString(1);
                            }
                            int bookcount = Integer.parseInt(Countrow);
                            if(bookcount==0)
                            {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('No books Found with the following Title');");
                                out.println("location='searchBook.jsp';");
                                out.println("</script>");
                            }
                            else
                            {
                            List<Book> books = new ArrayList<Book>();
                            while(rs.next()){
                                    Book book = new Book();
                                    book.setBookId(rs.getInt("id"));
                                    book.setBookName(rs.getString("bookName"));
                                    book.setAuthorName(rs.getString("authorName"));
                                    book.setISBN(rs.getString("ISBN"));
                                    book.setPublisher(rs.getString("publisher"));
                                    book.setYear(rs.getInt("year_of_publication"));
                                    book.setSubject(rs.getString("subject"));
                                    book.setTotalCopies(rs.getInt("totalcopies"));
                                    book.setAvailCopies(rs.getInt("availablecopies"));
                                    books.add(book);
                            }
                            request.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("successBookSearch.jsp");
                            requestDispatcher.forward(request, response);
                            //}
                        } 
                    }
                        catch (InstantiationException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }                    
                }
                else if(menuSelection.equals("authorname")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                          Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            PreparedStatement pst = null;
                            pst = connection.prepareStatement("select * from Books where authorname=?");
                            pst.setString(1, querySt);
                            rs = pst.executeQuery();
                            
                            PreparedStatement pst2 = null;
                            pst2 = connection.prepareStatement("select count(*) from Books where authorname=?");
                            pst2.setString(1, querySt);
                            rs2 = pst2.executeQuery();
                            
                            String Countrow="";
                            while(rs2.next())
                            {
                                Countrow = rs2.getString(1);
                            }
                            int bookcount = Integer.parseInt(Countrow);
                            if(bookcount==0)
                            {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('No books Found with the following Author');");
                                out.println("location='searchBook.jsp';");
                                out.println("</script>");
                            }
                            else
                            {
                            List<Book> books = new ArrayList<Book>();
                            while(rs.next()){
                                    Book book = new Book();
                                    book.setBookId(rs.getInt("id"));
                                    book.setBookName(rs.getString("bookName"));
                                    book.setAuthorName(rs.getString("authorName"));
                                    book.setISBN(rs.getString("ISBN"));
                                    book.setPublisher(rs.getString("publisher"));
                                    book.setYear(rs.getInt("year_of_publication"));
                                    book.setSubject(rs.getString("subject"));
                                    book.setTotalCopies(rs.getInt("totalcopies"));
                                    book.setAvailCopies(rs.getInt("availablecopies"));
                                    books.add(book);
                            }
                            request.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("successBookSearch.jsp");
                            requestDispatcher.forward(request, response);
                        } 
                    }
                        catch (InstantiationException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
                    else if(menuSelection.equals("subjectname")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                          Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            PreparedStatement pst = null;
                            pst = connection.prepareStatement("select * from Books where subject=?");
                            pst.setString(1, querySt);
                            rs = pst.executeQuery();
                            
                            PreparedStatement pst2 = null;
                            pst2 = connection.prepareStatement("select count(*) from Books where subject=?");
                            pst2.setString(1, querySt);
                            rs2 = pst2.executeQuery();
                            
                            String Countrow="";
                            while(rs2.next())
                            {
                                Countrow = rs2.getString(1);
                            }
                            int bookcount = Integer.parseInt(Countrow);
                            if(bookcount==0)
                            {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('No books Found with the following Subject');");
                                out.println("location='searchBook.jsp';");
                                out.println("</script>");
                            }
                            else
                            {
                            List<Book> books = new ArrayList<Book>();
                            while(rs.next()){
                                    Book book = new Book();
                                    book.setBookId(rs.getInt("id"));
                                    book.setBookName(rs.getString("bookName"));
                                    book.setAuthorName(rs.getString("authorName"));
                                    book.setISBN(rs.getString("ISBN"));
                                    book.setPublisher(rs.getString("publisher"));
                                    book.setYear(rs.getInt("year_of_publication"));
                                    book.setSubject(rs.getString("subject"));
                                    book.setTotalCopies(rs.getInt("totalcopies"));
                                    book.setAvailCopies(rs.getInt("availablecopies"));
                                    books.add(book);
                            }
                            request.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("successBookSearch.jsp");
                            requestDispatcher.forward(request, response);
                            //}
                        } 
                    }
                        catch (InstantiationException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                    }
                    else if(menuSelection.equals("yearpub")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                          Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            PreparedStatement pst = null;
                            pst = connection.prepareStatement("select * from Books where year_of_publication=?");
                            pst.setString(1, querySt);
                            rs = pst.executeQuery();
                            
                            PreparedStatement pst2 = null;
                            pst2 = connection.prepareStatement("select count(*) from Books where year_of_publication=?");
                            pst2.setString(1, querySt);
                            rs2 = pst2.executeQuery();
                            
                            String Countrow="";
                            while(rs2.next())
                            {
                                Countrow = rs2.getString(1);
                            }
                            int bookcount = Integer.parseInt(Countrow);
                            if(bookcount==0)
                            {
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('No books Found with the following Year of Publication');");
                                out.println("location='searchBook.jsp';");
                                out.println("</script>");
                            }
                            else
                            {
                            List<Book> books = new ArrayList<Book>();
                            while(rs.next()){
                                    Book book = new Book();
                                    book.setBookId(rs.getInt("id"));
                                    book.setBookName(rs.getString("bookName"));
                                    book.setAuthorName(rs.getString("authorName"));
                                    book.setISBN(rs.getString("ISBN"));
                                    book.setPublisher(rs.getString("publisher"));
                                    book.setYear(rs.getInt("year_of_publication"));
                                    book.setSubject(rs.getString("subject"));
                                    book.setTotalCopies(rs.getInt("totalcopies"));
                                    book.setAvailCopies(rs.getInt("availablecopies"));
                                    books.add(book);
                            }
                            request.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("successBookSearch.jsp");
                            requestDispatcher.forward(request, response);
                            //}
                        } 
                    }
                        catch (InstantiationException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
            }
        }
    }
}