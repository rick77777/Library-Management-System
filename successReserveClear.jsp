<%-- 
    Document   : successReserveClear
    Created on : 26 May, 2020, 3:33:16 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Reserve"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Clear Reservation Success</title>
</head>
<body bgcolor='white'>
<form action="AdminSection" method="post">
<!-- Page Heading -->
<table class="content" border='1' cellpadding='2' cellspacing='0' width='600'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h3>Library Management System: Clear Reservation Success</h3>
                </td></tr> 
        </table><br>

<%	Reserve reserve_book  = (Reserve)request.getAttribute("reserveBook");
 %>
 <br>
 <p><center>
Reservation for Book ID<i> <%=reserve_book.getBookId() %></i> for User ID <%=reserve_book.getUserName() %> was successfully cleared.
 </center>
</p>
<p>
    <center>
        Click <a href='adminsection.jsp'>here</a> to return to Admin section.<br/>
    </center>
    </p>
<p>
    <center>
        Click <a href="index.jsp">here</a> to log out.<br/>
    </center>
    </p>
</form>
</body>
</html>