<%-- 
    Document   : listBooks
    Created on : 25 Apr, 2020, 11:10:31 AM
    Author     : CHIRADIP
--%>

<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>   

<%@page import="Library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: List Books</title>
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
                <td><h2>Library Management System : List Books</h2>
                </td></tr> 
        </table><br>
<p>
<center>These are the following details of the books available.</center>
</p>
<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");

%>	

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

	</tr>
<%
}
%>
</table>

<br/><br/>

<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>