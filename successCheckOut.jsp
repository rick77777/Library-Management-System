<%-- 
    Document   : successCheckOut
    Created on : 25 Apr, 2020, 3:33:07 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.CheckOut"%>
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
<title>Library Management System: Checkout Success</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table class="content" border='1' cellpadding='2' cellspacing='0' width='600'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h3>Library Management System: Checkout Success</h3>
                </td></tr> 
        </table><br>
        
<%	CheckOut checkout = (CheckOut)request.getAttribute("checkout");
 %>
 <br>
 <p><center>
    Your request to checkout Book ID<i> <%=checkout.getBookId() %></i> by  <%=checkout.getUserName() %> was successful.<br></center></p>
    <!--<p>
    <center>
        Click <a href='checkOut.jsp'>here</a> to try another checkout/return.<br/>
    </center>
    </p>-->
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
</body>
</html>