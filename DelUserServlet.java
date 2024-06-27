/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DelUserServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String> errorMsgs=new ArrayList<String>();
        Connection con = null;  
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
            //send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        try {
            
            String username=request.getParameter("username").trim();
            
            Class.forName("com.mysql.jdbc.Driver");
             con =DriverManager.getConnection 
             ("jdbc:mysql://127.0.0.1:3306/library","root","");
             
             int userExists = 0;
             userExists = userExists(con, username);
             
             if(userExists==1)
             {
                    stmt = con.prepareStatement("select * from Checkout where username = ?");
                    stmt.setString(1, username);
                    rs = stmt.executeQuery();
                    int c=2992;

                    if(rs.next()==false){
                        c=0;
                       }
                    /**else if(username.length()==0)  {
                       errorMsgs.add("Please enter the username");
                       }
                    else{
                            errorMsgs.add("Given user has books yet to return");
                    }
                     **/
                    String user = new String();
                  //Send the ErrorPage view if there were errors
                  /*  if(!errorMsgs.isEmpty())  {
                                  RequestDispatcher view = request.getRequestDispatcher("addordeluser.jsp");
                                  view.forward(request, response);
                                  //return;
                          }      */

                   if(c==0)
                   {
                   stmt = con.prepareStatement("delete from Users where username = ?");
                   stmt.setString(1, username);
                   int result = stmt.executeUpdate();
                       if(result>0)
                       {
                           request.setAttribute("user", username);               
                           //Send the success view
                           RequestDispatcher view = request.getRequestDispatcher("successDelete.jsp");
                           view.forward(request, response);
                       }
                   }
                   else
                   {
                       request.setAttribute("user", username); 

                       RequestDispatcher view = request.getRequestDispatcher("unsuccDel.jsp");
                       view.forward(request, response);
                   }
             }
             else
             {
                request.setAttribute("user", username); 

                RequestDispatcher view = request.getRequestDispatcher("unsuccDel.jsp");
                view.forward(request, response);
             }
            
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
    }