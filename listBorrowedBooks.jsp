<%-- 
    Document   : listBorrowedBooks
    Created on : 25 Apr, 2020, 11:14:50 AM
    Author     : CHIRADIP
--%>

<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Library.model.CheckOut"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: List borrowed books</title>
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
                <td><h2>Library Management System: List borrowed books</h2>
                </td></tr> 
        </table><br>
<p>
<center>
    Following is the list of the borrowed books.
</center>
</p>
<%
List<CheckOut> checkout = (ArrayList<CheckOut>)request.getAttribute("checkout");
List<CheckOut> past_returndate = (ArrayList<CheckOut>)request.getAttribute("past_returndate");

%>	
<br/><br/>
<center>
List of books issued -

<table width='50%' border='1'>
<thead align='center'>
<th>Transaction Id</th>
<th>Book Id</th>
<th>UserName</th>
</thead>
<%
int trax=-1,tran=0;
for(CheckOut checkoutInstance:checkout){
    
    tran = checkoutInstance.getTransactionId();
    // System.out.println(s);
    if(tran>trax)
    {
%>	
	<tr align='center'>
            <td><%=tran%></td>	
            <td><%=checkoutInstance.getBookId()%></td>
            <td><%=checkoutInstance.getUserName()%></td>

	</tr>
<%
    trax = tran;
    }
}
%>
</table>
<br><br>

List of books which are past their return date - 

<table width='70%' border='1'>
<thead align='center'>
<th>Transaction Id</th>
<th>Book Id</th>
<th>UserName</th>
<th>Return Date</th>
</thead>
<%
for(CheckOut checkoutInstance:past_returndate){
%>	
	<tr align='center'>
            <td><%=checkoutInstance.getTransactionId()%></td>	
            <td><%=checkoutInstance.getBookId()%></td>
            <td><%=checkoutInstance.getUserName()%></td>
            <td><%=checkoutInstance.getReturnDate()%></td>

	</tr>
<%
}
%>
</table>
</center>
<br/><br/>

</form>

<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>