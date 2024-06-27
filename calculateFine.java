package Library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class calculateFine extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        List<String> errorMsgs = new ArrayList<String>();
        List<String> fine = new ArrayList<String>();

        // JDBC variables
        Connection con = null;
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        request.setAttribute("fine", fine);

        try {

            // Retrieve form parameters

            String userName = request.getParameter("username");
            String bookid = request.getParameter("bookid");

            
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
            else if (userName != null || bookid != null) {
                userExists = userExists(con, userName);

                if (userExists == 0) {
                    errorMsgs.add("User does not exist in system");
                } 
                else {
                    bookIssue = bookIssued(con, userName, book_id);
                }

                if (bookIssue != 1) {
                    errorMsgs.add("The User has not borrowed this book!");
                }
            }

            // Send the ErrorPage view if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher view = request.getRequestDispatcher("calcFine.jsp");
		view.forward(request, response);
		return;
            }
            // Execute logic
            else{
                
                PreparedStatement pst2 = null;
                
                pst2 = con.prepareStatement("Select TIMESTAMPDIFF(DAY,return_date,curDate()) From checkout WHERE book_id = ? and username = ?");
                pst2.setInt(1, book_id);
                pst2.setString(2, userName);
                 
                ResultSet rs = null;
                rs = pst2.executeQuery();
                 
                String timeStampDiff = "5";
                while(rs.next())
                {
                    timeStampDiff = rs.getString(1);
                }
                
                //Calculating Fine 
                
                int diff = Integer.parseInt(timeStampDiff);
                
                int finex = 0;
                if(diff<0)
                    finex = 0;
                else if(diff<=14)
                    finex = diff * 2;
                else if(diff<=28 && diff>14)
                    finex = 28 + (diff - 14) * 3;
                else if(diff>28)
                    finex = 70 + (diff - 28) * 5;
                                
                String charges = Integer.toString(finex);
                fine.add(charges);
                fine.add(userName);
                fine.add(bookid);                
           
                RequestDispatcher view = request.getRequestDispatcher("showFine.jsp");
                view.forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
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
}