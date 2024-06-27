package Library;

import Library.model.Book;
import Library.model.CheckOut;
import Library.model.Reserve;
import java.io.IOException;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class CheckOutServlet extends HttpServlet {

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
            String checkout_book = request.getParameter("checkout");
            String return_book = request.getParameter("return");
            String reserve_book = request.getParameter("reserve");
            String clr_reserve_book = request.getParameter("clr_reserve");
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/library", "root", "");
            
            String id = request.getParameter("bkgroup1");
            int book_id = Integer.parseInt(id);
            if (userName  == null) {
                errorMsgs.add("Please enter the user name");
                RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkOut.jsp");
                reqDispatcher.forward(request, response);
            }
            if (id == null) {
                errorMsgs.add("Please click on the book you want to checkout");
                RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkOut.jsp");
                reqDispatcher.forward(request, response);
            }
            if (!errorMsgs.isEmpty()) {
                setBooksIntoRequest(con, request, response);
            }
            // Checking out a book

            int userExists = 0, bookIssued = 0;


            if (checkout_book == null && return_book == null && reserve_book == null && clr_reserve_book == null) {
                errorMsgs.add("Please select either checkout/return/reserve book option!");
            }
            else if (checkout_book != null || return_book != null || reserve_book != null || clr_reserve_book != null) {
                userExists = userExists(con, userName);

                if (userExists == 0) {
                    errorMsgs.add("User does not exist in system");
                } else {
                    bookIssued = bookAlreadyIssued(con, userName, book_id);
                }

                if (bookIssued == 1) {
                    errorMsgs.add("The same user has already borrowed this book!");
                }
                int bookcount=0;
                
                PreparedStatement stmt = null;
                ResultSet rs = null;
                
                stmt = con.prepareStatement("select count(book_id) from Checkout where username = ?");
                stmt.setString(1, userName);
                rs = stmt.executeQuery();
                
                String Countrow="";
                while(rs.next())
                {
                    Countrow = rs.getString(1);
                }
                bookcount = Integer.parseInt(Countrow);

                
                if (userExists == 1 && checkout_book != null) {
                    checkout(con, book_id, bookIssued, request, userName, response, errorMsgs, bookcount);
                }
                else if (userExists == 1 && return_book != null) {
                    returnBook(con, book_id, bookIssued, userName, request, response, errorMsgs);
                }
                else if (userExists == 1 && reserve_book != null) {
                    reserveBook(con, book_id, bookIssued, userName, request, response, errorMsgs);
                }
                else if(userExists == 1 && clr_reserve_book != null) {
                    clear_reservation(con, book_id, userName, request, response, errorMsgs);
                }
            }

            // Send the ErrorPage view if there were errors
            if (!errorMsgs.isEmpty()) {
                setBooksIntoRequest(con, request, response);
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

    private int bookAlreadyIssued(Connection con, String userName, int book_id) throws SQLException {
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

    private void incrementAvailableCopies(int availcopies, Connection con, int book_id) throws SQLException {
        PreparedStatement pst4 = null;
        availcopies += 1;
        pst4 = con.prepareStatement("update Books set availablecopies=? where id=?");
        pst4.setInt(1, availcopies);
        pst4.setInt(2, book_id);

        pst4.executeUpdate();
        // Send the success view
    }

    private void decrementAvailableCopies(int oldcopies, Connection con, int book_id) throws SQLException {
        PreparedStatement pst4 = null;
        oldcopies -= 1;
        pst4 = con.prepareStatement("update Books set availablecopies=? where id=?");
        pst4.setInt(1, oldcopies);
        pst4.setInt(2, book_id);
        pst4.executeUpdate();
    }

    private void insertIntoCheckout(Connection con, int book_id, String userName, java.util.Date date) throws SQLException, ParseException {
        PreparedStatement pst2 = null;
                        
        java.sql.Date mySqlDate = new java.sql.Date(date.getTime());
        pst2 = con.prepareStatement("insert into Checkout values(null,?,?,?)");
        pst2.setInt(1, book_id);
        pst2.setString(2, userName);
        pst2.setDate(3, mySqlDate);
        pst2.executeUpdate();
    }

    private void setCheckoutIntoRequest(int book_id, String userName, HttpServletRequest request) {
        CheckOut checkOut = new CheckOut();
        checkOut.setBookId(book_id);
        checkOut.setUserName(userName);
        request.setAttribute("checkout", checkOut);
    }

    private void removeFromCheckout(Connection con, String username, int book_id) throws SQLException {
        PreparedStatement pst2 = null;
        pst2 = con.prepareStatement("delete from Checkout where username=? and book_id=?");
        pst2.setString(1, username);
        pst2.setInt(2, book_id);
        pst2.executeUpdate();
    }
    
    private void insertIntoReserve(Connection con, int book_id, String userName) throws SQLException, ParseException {
        PreparedStatement pst2 = null;
                        
        pst2 = con.prepareStatement("insert into Reserve values(null,?,?)");
        pst2.setInt(1, book_id);
        pst2.setString(2, userName);
        pst2.executeUpdate();
    }
    
    private void removeFromReserve(Connection con, String username, int book_id) throws SQLException {
        PreparedStatement pst2 = null;
        pst2 = con.prepareStatement("delete from Reserve where username=? and book_id=?");
        pst2.setString(1, username);
        pst2.setInt(2, book_id);
        pst2.executeUpdate();
    }
    
    private boolean alreadyReserved(Connection con, String userName, int book_id) throws SQLException {
        PreparedStatement pst;
        ResultSet rsBooks;
        boolean aRs = false;
        pst = con.prepareStatement("select book_id from Reserve where Username=? and book_id=?");
        pst.setString(1, userName);
        pst.setInt(2, book_id);
        rsBooks = pst.executeQuery();
        if (rsBooks.next()) {            
            aRs = true;
        }
        return aRs;
    }
    
    private void setReturnIntoRequest(int book_id, String userName, HttpServletRequest request) {
        CheckOut checkOut = new CheckOut();
        checkOut.setBookId(book_id);
        checkOut.setUserName(userName);
        request.setAttribute("returnBook", checkOut);
    }
    
    private void setReserveIntoRequest(int book_id, String userName, HttpServletRequest request) {
        Reserve reserve = new Reserve();
        reserve.setBookId(book_id);
        reserve.setUserName(userName);
        request.setAttribute("reserveBook", reserve);
    }

    private SimpleDateFormat getDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("MM/dd/yy");
        return dateFormat;
    }

    private void setBooksIntoRequest(Connection con, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Statement statement = con.createStatement();
        ResultSet rsBookList = statement.executeQuery("SELECT * FROM Books");

        List<Book> books = new ArrayList<Book>();
        while (rsBookList.next()) {
            Book book = new Book();
            book.setBookId(rsBookList.getInt("id"));
            book.setBookName(rsBookList.getString("bookName"));
            book.setAuthorName(rsBookList.getString("authorName"));
            book.setISBN(rsBookList.getString("ISBN"));
            book.setPublisher(rsBookList.getString("publisher"));
            book.setYear(rsBookList.getInt("year_of_publication"));
            book.setSubject(rsBookList.getString("subject"));
            book.setTotalCopies(rsBookList.getInt("totalcopies"));
            book.setAvailCopies(rsBookList.getInt("availablecopies"));
            books.add(book);
        }

        request.setAttribute("books", books);
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkOut.jsp");
        reqDispatcher.forward(request, response);
    }
    
    private String displayFine(Connection con, String userName, int book_id) throws SQLException 
    {
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
                return charges;
    }

    private void checkout(Connection con, int book_id, int bookIssued, HttpServletRequest request, String userName, HttpServletResponse response, List<String> errorMsgs, int bkcount) throws SQLException, IOException, ParseException, ServletException {
        ResultSet rsBookById;
        int availableCopies;
        PreparedStatement pst = null;
        pst = con.prepareStatement("select availablecopies from Books where id=?");
        pst.setInt(1, book_id);
        rsBookById = pst.executeQuery();
        if(bkcount<5){
            if (rsBookById.next()) {
                availableCopies = rsBookById.getInt(1);
                if (availableCopies > 0) {
                    if (bookIssued == 0) {
                        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofreturn"));
                        Date today = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                        
                        long diffInMillies = Math.abs(today.getTime() - date.getTime());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        //String dif = Long.toString(diff);
                        if(diff>10 || diff<0)
                        {
                            errorMsgs.add("A book can be borrowed cannot be borrowed for "+diff+" days !!! \n A book can be borrowed for maximum 10 days." );
                        }
                        else
                        {
                            insertIntoCheckout(con, book_id, userName, date);
                            decrementAvailableCopies(availableCopies, con, book_id);
                            setCheckoutIntoRequest(book_id, userName, request);
                            // Send the success checkout view
                            RequestDispatcher view = request
                                    .getRequestDispatcher("successCheckOut.jsp");
                            view.forward(request, response);
                        }
                    }
                } else {
                    errorMsgs.add("There are no copies of the requested book available.");
                }
            }
        }
        else{
            errorMsgs.add("5 books have already been borrowed by the User !!!");
        }
    }

    private void returnBook(Connection con, int book_id, int bookIssued, String userName, HttpServletRequest request, HttpServletResponse response, List<String> errorMsgs) throws IOException, SQLException, ServletException {
        ResultSet rsBooks;
        int totcopies;
        int availcopies;
        String x,user;
        PreparedStatement pst = null;
        pst = con.prepareStatement("select totalcopies,availablecopies from Books where id=?");
        pst.setInt(1, book_id);
        rsBooks = pst.executeQuery();
        if (rsBooks.next()) {
            totcopies = rsBooks.getInt(1);
            availcopies = rsBooks.getInt(2);
            if ((availcopies + 1) <= totcopies) {
                if (bookIssued == 1) {
                    x = displayFine(con, userName, book_id);
                    removeFromCheckout(con, userName, book_id);
                    incrementAvailableCopies(availcopies, con, book_id);
                    user = userName + " Fine is : Rs. " + x;
                    setReturnIntoRequest(book_id, user, request);
                    RequestDispatcher view = request.getRequestDispatcher("successReturn.jsp");
                    view.forward(request, response);
                } else {
                    errorMsgs.add("The given user has not borrowed this book!");
                }
            } else {
                //errorMsgs.add("Available copies of the book cannot exceed total copies.");
                errorMsgs.add("The given user has not borrowed this book!");
            }
        }
    }
    
    
    private void reserveBook(Connection con, int book_id, int bookIssued, String userName, HttpServletRequest request, HttpServletResponse response, List<String> errorMsgs) throws IOException, SQLException, ServletException, ParseException {
        
        if(bookIssued == 1)
        {
            errorMsgs.add("The given user has already borrowed this book!");
        }
        else
        {
            boolean aRs = false;
            aRs = alreadyReserved(con, userName, book_id);
            if(aRs == true)
            {
                errorMsgs.add("The given user has already reserved this book!");
            }
            else
            {
                ResultSet rsBooks;
                
                int availcopies = 0;
                PreparedStatement pst = null;
                pst = con.prepareStatement("select availablecopies from Books where id=?");
                pst.setInt(1, book_id);
                rsBooks = pst.executeQuery();
                if (rsBooks.next()) 
                {
                    availcopies = rsBooks.getInt(1);
                }
                if(availcopies>0)
                {
                    errorMsgs.add("The book is available! No need to reserve it.");
                }
                else
                {
                    insertIntoReserve(con, book_id, userName);
                    setReserveIntoRequest(book_id, userName, request);
                    RequestDispatcher view = request.getRequestDispatcher("successReserve.jsp");
                    view.forward(request, response);
                }
            }
        }
    }
    
    private void clear_reservation(Connection con, int book_id, String userName, HttpServletRequest request, HttpServletResponse response, List<String> errorMsgs) throws IOException, SQLException, ServletException, ParseException {
        
        PreparedStatement pst;
        ResultSet rs;
        int bookReserved = 0;
        pst = con.prepareStatement("select count(*) from Reserve where Username=? and book_id=?");
        pst.setString(1, userName);
        pst.setInt(2, book_id);
        rs = pst.executeQuery();
        String Countrow="";
                while(rs.next())
                {
                    Countrow = rs.getString(1);
                }
                bookReserved = Integer.parseInt(Countrow);
        if(bookReserved!=1)
        {
             errorMsgs.add("No Reservation found to clear !!");
        }
        else
        {
            removeFromReserve(con, userName, book_id);
            setReserveIntoRequest(book_id, userName, request);
            RequestDispatcher view = request.getRequestDispatcher("successReserveClear.jsp");
            view.forward(request, response);
        }
    }
}