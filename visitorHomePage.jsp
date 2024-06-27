<%-- 
    Document   : visitorHomePage
    Created on : 25 Apr, 2020, 2:50:41 PM
    Author     : CHIRADIP
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

String connectionURL = "jdbc:mysql://localhost:3306/library";

Connection connection = null;

Statement statement = null;

ResultSet rs = null,rs2 = null;

%>
<html>
<title>Library Management System: Visitor's Home Page</title>
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
<body>
    <table class="content" border='1' cellpadding='2' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>Welcome to the Library's Visitor Home Page</h2>
                </td></tr> 
        </table><br>
<%

Class.forName("com.mysql.jdbc.Driver").newInstance();

connection = DriverManager.getConnection(connectionURL, 
"root","");

statement = connection.createStatement();
rs = statement.executeQuery("SELECT * FROM Books");
%>	
<br>
<h3><center>Here is the select range of books available here -</center></h3>
<table width='100%' border='1'>
<thead align='center'>
<th>Book Id</th>
<th>Book Name</th>
<th>Author Name</th>
<th>ISBN</th>
<th>Publisher</th>
<th>Subject</th>
<th>Year of Publication</th>
<th>Available Copies</th>
<th>Total Copies</th>
</thead>
<%
while (rs.next()) {
%>	
	<tr align='center'>
		<td><%=rs.getInt("id")%></td>
		<td><%=rs.getString("bookName")%></td>
		<td><%=rs.getString("authorName")%></td>
		<td><%=rs.getString("ISBN")%></td>
		<td><%=rs.getString("publisher")%></td>
                <td><%=rs.getString("subject")%></td>
		<td><%=rs.getInt("year_of_publication")%></td>
		<td><%=rs.getInt("availablecopies")%></td>
		<td><%=rs.getInt("totalcopies") %>
	</tr>
<%
}
%>
</table>

<br/><br/><br/>

<%
rs.close();
%>
<center>Click <a href="index.jsp">here</a> to go to Home Page.</center>
</body>
</html>
