/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Library.model.Book;
import Library.model.CheckOut;
import Library.model.User;
import java.io.IOException;
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
public class AdminSection extends HttpServlet {

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String menuSelection = req.getParameter("menuselection");
		
                if(menuSelection==null)
                {
                    List<String> errorMsgs=new ArrayList<String>();
                    req.setAttribute("errorMsgs", errorMsgs);
                    errorMsgs.add("Please select an option !!");
                    RequestDispatcher view = req.getRequestDispatcher("adminsection.jsp");
                    view.forward(req, resp);
                }    
                else if(menuSelection.equals("listbooks")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                          Connection connection = null;

                          Statement statement = null;

                          ResultSet rs = null,rs2 = null;
                          Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            statement = connection.createStatement();
                            rs = statement.executeQuery("SELECT * FROM Books");

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
                            req.setAttribute("books", books);
                                                        
                            RequestDispatcher requestDispatcher = req.getRequestDispatcher("listBooks.jsp");
                            requestDispatcher.forward(req, resp);
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
                }else if(menuSelection.equals("listborrowedbooks")){
                    String connectionURL = "jdbc:mysql://localhost:3306/library";

                    try {
                            Connection connection = null;

                            Statement statement = null,statement2 = null;

                            ResultSet rs = null,rs2 = null;
                            Class.forName("com.mysql.jdbc.Driver").newInstance();

                            connection = DriverManager.getConnection(connectionURL, 
                            "root","");

                            statement = connection.createStatement();
                            statement2 = connection.createStatement();
                            
                            rs = statement.executeQuery("SELECT * FROM Checkout order by transaction_Id");
                            List<CheckOut> checkOut= new ArrayList<CheckOut>();
                            while(rs.next()){
                                CheckOut checkOutInstance = new CheckOut();
                                checkOutInstance.setBookId(rs.getInt(2));
                                checkOutInstance.setTransactionId(rs.getInt(1));
                                checkOutInstance.setUserName(rs.getString("username"));
                                checkOut.add(checkOutInstance);
                            }
                            req.setAttribute("checkout",checkOut);
                            //java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                            //rs = statement.executeQuery("SELECT curdate(); ");
                            
                            long millis=System.currentTimeMillis();  
                            java.sql.Date date=new java.sql.Date(millis);  
                            
                            /**PreparedStatement stmt = null;
                            stmt = connection.prepareStatement("SELECT * FROM Checkout where return_date < ?");
                            stmt.setDate(1, date);
                            rs2 = stmt.executeQuery();**/
                            rs2 = statement2.executeQuery("SELECT * FROM Checkout where return_date < curdate() ");
                            //order by transaction_Id 
                            List<CheckOut> past_returndate = new ArrayList<CheckOut>();
                            while(rs2.next()){
                                CheckOut checkOutInstance = new CheckOut();
                                checkOutInstance.setBookId(rs2.getInt("book_Id"));
                                checkOutInstance.setTransactionId(rs2.getInt("transaction_Id"));
                                checkOutInstance.setUserName(rs2.getString("username"));
                                checkOutInstance.setReturnDate(rs2.getDate(4));
                                past_returndate.add(checkOutInstance);
                            }
                            req.setAttribute("past_returndate", past_returndate);

                            RequestDispatcher requestDispatcher = req.getRequestDispatcher("listBorrowedBooks.jsp");
                            requestDispatcher.forward(req, resp);
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
                    
                }else if(menuSelection.equals("checkoutbook")){
			String connectionURL = "jdbc:mysql://localhost:3306/library";

			try {
				Connection connection = null;

				Statement statement = null;

				ResultSet rs = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				connection = DriverManager.getConnection(connectionURL, 
				"root","");

				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * FROM Books");
				
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
				
				req.setAttribute("books", books);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkOut.jsp");
				requestDispatcher.forward(req, resp);
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
		}else if(menuSelection.equals("listusers")){
			String connectionURL = "jdbc:mysql://localhost:3306/library";

			try {
				Connection connection = null;

				Statement statement = null;

				ResultSet rs = null,rs2 = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				connection = DriverManager.getConnection(connectionURL, 
				"root","");

				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * FROM Users");
				
				List<User> users = new ArrayList<User>();
				while(rs.next()){
					User user = new User();
					user.setFirstName(rs.getString("firstName"));
					user.setSurname(rs.getString("surname"));
					user.setAge(rs.getInt("age"));
					user.setGender(rs.getString("gender"));
					user.setUsername(rs.getString("username"));
					//user.setPassword(rs.getString("password"));
					users.add(user);
				}
				
				req.setAttribute("users", users);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("listUsers.jsp");
				requestDispatcher.forward(req, resp);
			} catch (InstantiationException e) {
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