<%-- 
    Document   : successReserve
    Created on : 26 May, 2020, 8:56:02 PM
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
<title>Library Management System: Reserve Book Success</title>
</head>
<body bgcolor='white'>
<form action="AdminSection" method="post">
<!-- Page Heading -->
<table class="content" border='1' cellpadding='2' cellspacing='0' width='600'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h3>Library Management System: Reserve Book Success</h3>
                </td></tr> 
        </table><br>

<%	Reserve reserve_book  = (Reserve)request.getAttribute("reserveBook");
 %>
 <br>
 <p><center>
Your request to reserve Book ID<i> <%=reserve_book.getBookId() %></i> by User ID <%=reserve_book.getUserName() %> was successful.
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