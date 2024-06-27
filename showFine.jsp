<%-- 
    Document   : showFine
    Created on : 16 May, 2020, 11:35:26 AM
    Author     : CHIRADIP
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Display Fine</title>
<style>
th
{
    background-color: #cab3ff;
}
td, th 
{ 
  text-align: center;
  padding: 6px;
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
<body bgcolor='white'>
<!-- Page Heading -->
        <table class="content" border='1' cellpadding='2' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>Library Management System: Display Fine</h2>
                </td></tr> 
        </table><br>
<%	List<String> fine = (ArrayList<String>)request.getAttribute("fine");
        String charges = fine.get(0).trim();
        String user = fine.get(1);
        String bookid = fine.get(2);
        int s = Integer.parseInt(charges);
        int bk = Integer.parseInt(bookid);
        String charge = null;
        if(s==0)
            charge = " No Fine Due!!";
        else 
            charge = "Rs. "+charges;
 %>

<h3><center>Displaying Fine Due against respective User Name with respective Book ID -</center></h3>
<center>
<table width='40%' border='1'>
<thead align='center'>
<th>Book Id</th>
<th>User Name</th>
<th>Fine Due</th>
</thead>
<tr align='center'>
	<td><%=bk%></td>
	<td><%=user%></td>
	<td><%=charge%></td>
</tr>
</table>
</center>
<br><br><br>
<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="calcFine.jsp">here</a> to check more fines.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>