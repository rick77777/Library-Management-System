<%-- 
    Document   : successGreeting
    Created on : 25 Apr, 2020, 8:35:47 AM
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
        <title>Library Management System: Success Greeting Page</title>
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
                <td><h2>Library Management System: User Page</h2>
                </td></tr> 
        </table><br>
<%	List<CheckOut> checkout = (ArrayList<CheckOut>)request.getAttribute("checkedOutItems");
 %>

 <p> <center> You have successfully logged in!!! </center></p>

<u style="margin-left:230px">Books currently borrowed by you:</u><br/><br/>	
<center>
<table width='70%' border='1'>
<thead align='center'>
<th>Transaction Id</th>
<th>Book Id</th>
<th>Return Date</th>
</thead>
<%
    String s="";
for(CheckOut checkedOutItem: checkout) {
%>	
	<tr align='center'>
		<td><%=checkedOutItem.getTransactionId()%></td>
		<td><%=checkedOutItem.getBookId()%></td>
                <td><%=checkedOutItem.getReturnDate()%></td>
	</tr>
<%
    s = checkedOutItem.getUserName();
}
%>
</table>
</center>
<br/><br/>

<p> <center> You are currently logged in with username <b> '<% out.print(s); %>'</b> </center></p>

<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");

for(Book book:books){
}
session.setAttribute("userId", s);
%>

</table>

<br><br>
<center>
    <a href='editUser.jsp'><label style="font-size:18px" >Edit Your Details</label></a><br><br><br>
    <a href='searchBook.jsp'><label style="font-size:18px" >Search for a Book</label></a><br><br>
</center>
<br/><br/>
<center>Click <a href='index.jsp'>here</a> to logout</center><br/>

</body>
</html>
