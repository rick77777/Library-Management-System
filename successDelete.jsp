<%-- 
    Document   : successDelete
    Created on : 17 May, 2020, 11:58:32 AM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Library.model.CheckOut"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library Management System: Success Deletion Page</title>
        <style>
        td, th 
        { 
          text-align: center;
          padding: 6px;
        }
        th
        {
            background-color: #cab3ff;
        }
        tr:nth-child(even) {
          background-color: #dddddd;
        }
        .content {
                    max-width: 100%;
                    margin: auto;
                    padding: 20px;
                 }

        </style>
    </head>
   
 <body bgcolor='white'>
<!-- Page Heading -->

<table class="content" border='1' cellpadding='2' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>Library Management System: Success Deletion Page</h2>
                </td></tr> 
        </table><br>

        <%	String user = (String)request.getAttribute("user");
 %>
 <p> <center> You have successfully deleted the User Account with User Name <b><%=user%></b>!!! </center></p>


<br/><br/>
<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href='index.jsp'>here</a> to logout</center><br/>

</body>
</html>
