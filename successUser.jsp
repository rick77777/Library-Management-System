<%-- 
    Document   : successUser
    Created on : 25 Apr, 2020, 8:36:22 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Add User Success</title>
<style>
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
         
.buttony {
              background-color: #a64dff;
              border: none;
              color: white;
              padding: 10px;
              text-align: center;
              text-decoration: none;
              display: inline-block;
              font-size: 15px;
              margin: 2px;
              cursor: pointer;
              border-radius: 5px;
            }

</style>
</head>

<body bgcolor='white'>
<!-- Page Heading -->
        <table class="content" border='1' cellpadding='2' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>Library Management System: Add User Success</h2>
                </td></tr> 
        </table><br>
<%	User user = (User)request.getAttribute("user");
 %>
<p>
<center>
Your request to add  <b> <%=user.getUsername() %></b>  was successful.
</center>
</p>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>
