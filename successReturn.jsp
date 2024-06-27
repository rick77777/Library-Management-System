<%-- 
    Document   : successReturn
    Created on : 25 Apr, 2020, 3:34:08 PM
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
<title>Library Management System: Return Book Success</title>
</head>
<body bgcolor='white'>
<form action="AdminSection" method="post">
<!-- Page Heading -->
<table class="content" border='1' cellpadding='2' cellspacing='0' width='600'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h3>Library Management System: Return Book Success</h3>
                </td></tr> 
        </table><br>

<%	CheckOut return_book  = (CheckOut)request.getAttribute("returnBook");
        String x = return_book.getUserName() ;
        int pos = x.indexOf("Fine is : Rs.");
        String name = x.substring(0,pos);
        String fine = x.substring(pos,x.length());
 %>
 <br>
 <p><center>
     Your request to return Book ID<i> <%=return_book.getBookId() %></i> by User ID <%=name %> was successful.<br><br>
     <%=fine %>
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