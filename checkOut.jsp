<%-- 
    Document   : checkOut
    Created on : 25 Apr, 2020, 3:29:00 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.CheckOut"%>
<%@page import="Library.model.Book"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Checkout/Return</title>
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

        <table class="content" border='1' cellpadding='2' cellspacing='0' width='600'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h3>Library Management System : Checkout/Return</h3>
                </td></tr> 
        </table><br>

<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");
%>	
<form action='CheckOutServlet' method='POST'>
<table width='100%' border='1'>
<thead align='center'>
<th>Book Id</th>
<th>Book Name</th>
<th>Author Name</th>
<th>ISBN</th>
<th>Publisher</th>
<th>Subject</th>
<th>Year of Publication</th>
<th>Total Copies</th>
<th>Available Copies</th>
</thead>
<%
for(Book book:books){
%>	
	<tr align='center'>
		<td><%=book.getBookId()%></td>
		<td><%=book.getBookName()%></td>
		<td><%=book.getAuthorName()%></td>
		<td><%=book.getISBN()%></td>
		<td><%=book.getPublisher()%></td>
                <td><%=book.getSubject()%></td>
		<td><%=book.getYear()%></td>
		<td><%=book.getTotalCopies()%></td>
		<td><%=book.getAvailCopies() %></td>
		<td><input type='radio' name='bkgroup1' value=<%=book.getBookId()%> /></td>

	</tr>
<%
}
%>
</table>

<br/><br/><br/><br/>


<%
	CheckOut checkout = new CheckOut();
	if(request.getAttribute("errorMsgs") != null){
		%>
		<div>
		<%="Please correct the following errors!!!!" %>
		</div>
		
<% 
		java.util.List<String> errorMsgs = (java.util.List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
%>
		<li> <%= errorMsgs%></li>		
<%		}
		
		if((CheckOut)request.getAttribute("checkout") != null){
			checkout = (CheckOut)request.getAttribute("checkout");	
		}	
	}
%>



User Name : <input type='text' name='username' value="<%=checkout.getUserName()%>"><br/><br/>

Date of Return : <input type="date" name="dateofreturn" placeholder="yyyy-mm-dd" />
<input type='submit' name='checkout' value='Checkout book'/>
<input type='submit' name='return' value='Return book'/>
<input type='submit' name='reserve' value='Reserve book'/>
<input type='submit' name='clr_reserve' value='Clear Reservation'/>

</form>

<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>