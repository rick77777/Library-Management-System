package Library;

import Library.model.Book;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author CHIRADIP
 */
public class BulkEntryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        Part filePart = request.getPart("filex");
 
        Connection connection = null;
        List<String> errorMsgs=new ArrayList<String>();
        
        //JDBC variables
		 
        PrintWriter out = response.getWriter();  
	PreparedStatement pstmt = null;
        
        //send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
	
	//Retrieve form parameters
        String menuSelection = request.getParameter("menuselection");	
		
        //Verify form parameters
        boolean isthereafile;
        if(filePart.getSize()>0){
            isthereafile = true;
        }
        if(filePart.getSize()<=0){
            isthereafile = false;
            errorMsgs.add("Please select a file to upload");
        }
	if(menuSelection==null){
            errorMsgs.add("Please select an option to proceed");
        }
        //Send the ErrorPage view if there were errors
	if(!errorMsgs.isEmpty())  {
		RequestDispatcher view = request.getRequestDispatcher("bulkEntry.jsp");
		view.forward(request, response);
		return;
        }
        else{
		
                if(menuSelection.equals("user")){
                    try {
 
                            Class.forName("com.mysql.jdbc.Driver");
                            connection =DriverManager.getConnection 
                            ("jdbc:mysql://127.0.0.1:3306/library","root","");

                            String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = connection.prepareStatement(sql);


                            InputStream fileContent = filePart.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
                            String lineText = null;

                            br.readLine(); // skip header line

                            while ((lineText = br.readLine()) != null) {
                                String data[] = lineText.toString().split(",");
                                String firn = data[0];
                                String surn = data[1];
                                String age = data[2];
                                String gen = data[3];
                                String mail = data[4];
                                String usname = data[5];
                                String pssw = data[6];

                                statement.setString(1, firn);
                                statement.setString(2, surn);
                                statement.setString(3, age);
                                statement.setString(4, gen);
                                statement.setString(5, mail);
                                statement.setString(6, usname);
                                statement.setString(7, pssw);

                                statement.executeUpdate();
                            }

                                RequestDispatcher view = request.getRequestDispatcher("successBulkEntry.jsp");
                                view.forward(request, response);
                                return;
                        }
                        
                        catch (IOException | SQLException e) {
                            e.printStackTrace();
                        } 
                        catch (ClassNotFoundException ex) {
                            Logger.getLogger(BulkEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(menuSelection.equals("book")){
                        
                        try {
 
                            Class.forName("com.mysql.jdbc.Driver");
                            connection =DriverManager.getConnection 
                            ("jdbc:mysql://127.0.0.1:3306/library","root","");

                            String sql = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = connection.prepareStatement(sql);


                            InputStream fileContent = filePart.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
                            String lineText = null;

                            br.readLine(); // skip header line

                            while ((lineText = br.readLine()) != null) {
                                String data[] = lineText.toString().split(",");
                                String id = data[0];
                                String bkname = data[1];
                                String aname = data[2];
                                String isbn = data[3];
                                String pub = data[4];
                                String sub = data[5];
                                String year = data[6];
                                String tot = data[7];
                                

                                statement.setString(1, id);
                                statement.setString(2, bkname);
                                statement.setString(3, aname);
                                statement.setString(4, isbn);
                                statement.setString(5, pub);
                                statement.setString(6, sub);
                                statement.setString(7, year);
                                statement.setString(8, tot);
                                statement.setString(9, tot);

                                statement.executeUpdate();
                            }

                                RequestDispatcher view = request.getRequestDispatcher("successBulkEntry.jsp");
                                view.forward(request, response);
                                return;
                        }
                        catch (IOException | SQLException e) {
                            e.printStackTrace();
                        } 
                        catch (ClassNotFoundException ex) {
                            Logger.getLogger(BulkEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        }
    }
}