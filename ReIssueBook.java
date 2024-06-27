package Library;

import Library.model.CheckOut;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHIRADIP
 */
public class ReIssueBook extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        List<String> errorMsgs = new ArrayList<String>();

        // JDBC variables
        Connection con = null;
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);

        try {

            // Retrieve form parameters

            String userName = request.getParameter("username");
            String bookid = request.getParameter("bookid");
            String returndate = request.getParameter("dateofreturn");

            
            int book_id = Integer.parseInt(bookid);
            
            if (bookid== null || book_id<1) {
                errorMsgs.add("Please Enter a valid Book ID");
            }
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/library", "root", "");

            int userExists = 0, bookIssue = 100;

            if (userName.length() == 0) {
                errorMsgs.add("Please Enter the User Name");
            }
            else if (userName != null || bookid != null || returndate !=null) {
                userExists = userExists(con, userName);

                if (userExists == 0) {
                    errorMsgs.add("User does not exist in system");
                } 
                else {
                    bookIssue = bookIssued(con, userName, book_id);
                }

                if (bookIssue != 1) {
                    errorMsgs.add("The User has not borrowed this book!");
                    //errorMsgs.add(returndate);
                }
            }

            // Send the ErrorPage view if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher view = request.getRequestDispatcher("reissueBook.jsp");
		view.forward(request, response);
		return;
            }
            // Execute logic
            else{
                PreparedStatement pst2 = null;
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofreturn")); 
                Date today = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                        
                long diffInMillies = Math.abs(today.getTime() - date.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                //String dif = Long.toString(diff);
                if(diff>10 || diff<0)
                   {
                       errorMsgs.add("A book can be borrowed cannot be borrowed for "+diff+" days !!! \n A book can be borrowed for maximum 10 days." );
                       RequestDispatcher view = request.getRequestDispatcher("reissueBook.jsp");
                       view.forward(request, response);
                   }
                else
                {
                    java.sql.Date mySqlDate = new java.sql.Date(date.getTime());

                    pst2 = con.prepareStatement("UPDATE checkout SET return_date = ? WHERE book_id = ? and username = ?;");
                    pst2.setDate(1, mySqlDate);
                    pst2.setInt(2, book_id);
                    pst2.setString(3, userName);

                    pst2.executeUpdate();
                    CheckOut checkOut = new CheckOut();
                    checkOut.setBookId(book_id);
                    checkOut.setUserName(userName);
                    request.setAttribute("checkout", checkOut);
                    RequestDispatcher view = request.getRequestDispatcher("successReissue.jsp");
                    view.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**public static boolean isAlpha(String s) {
		return s != null && s.matches("^[a-zA-Z]*$");
	}**/
    

    private int userExists(Connection con, String userName) throws SQLException {
        PreparedStatement stmt = null;

        stmt = con.prepareStatement("select username from Users where username=?");
        stmt.setString(1, userName);
        ResultSet rsUser = stmt.executeQuery();
        int userExists = 0;
        if (rsUser.next()) {
            userExists = 1;
        }
        return userExists;
    }

    private int bookIssued(Connection con, String userName, int book_id) throws SQLException {
        PreparedStatement pst;
        ResultSet rsBooks;
        int bookAlreadyIssued = 0;
        pst = con.prepareStatement("select book_id from Checkout where Username=? and book_id=?");
        pst.setString(1, userName);
        pst.setInt(2, book_id);
        rsBooks = pst.executeQuery();
        if (rsBooks.next()) {            
            bookAlreadyIssued = 1;
        }
        return bookAlreadyIssued;
    }

    private SimpleDateFormat getDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yyyy");
        return dateFormat;
    }
}
