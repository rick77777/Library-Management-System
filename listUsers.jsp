<%-- 
    Document   : listUsers
    Created on : 25 Apr, 2020, 3:30:50 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Library Management System: User Details</title>
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
                <td><h2>Welcome to the User Details Page</h2>
                </td></tr> 
        </table><br>
        <p>
        <center>
            These are the User Details registered into the system.
        </center>
        </p>
<%

List<User> users = (ArrayList<User>)request.getAttribute("users");
%>	
<table width='100%' border='1'>
<thead align='center' >
<th>First Name</th>
<th>Surname</th>
<th>Age</th>
<th>Gender</th>
<th>Username</th>
</thead>
<%
for(User user:users){
%>	
	<tr align='center'>
		<td><%=user.getFirstName()%></td>
		<td><%=user.getSurname()%></td>
		<td><%=user.getAge()%></td>
		<td><%=user.getGender()%></td>
		<td><%=user.getUsername()%></td>
	</tr>
<%
}
%>
</table>

<br/><br/><br/><br/>

<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>