<%-- 
    Document   : addUser
    Created on : 25 Apr, 2020, 8:33:20 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Add a new user</title>
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
                <td><h2>Library Management System: Add a new user</h2>
                </td></tr> 
        </table><br>
<p>
<center>
This form allows you to add a new user.
</center>
</p>

<%
	User user = new User();
	if(request.getAttribute("errorMsgs") != null){
		%>
		<div>
		<%="Please correct the following errors!!!!" %>
		</div>
		
<% 
		java.util.List<String> errorMsgs = (java.util.List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
%>
		<li> <%= errorMsg%></li>		
<%		}
		
		user = (User)request.getAttribute("user");
		
	}
%>

<form action='AddUserServlet' method='POST'>
<table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td>First Name : <input type='text' name='firstname' value="<%=user.getFirstName()%>"> <br/>
                </td>
            </tr>
            <tr align='center'>
                <td>Last Name : <input type='text'name='surname' value="<%=user.getSurname()%>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Age : <input type='text' name='age' value="<%=user.getAge()%>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Gender (M/F) : <input type='text' name='gender' value="<%=user.getGender() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>E-mail ID : <input type='text' name='email' value="<%=user.getEmail() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>User Name : <input type='text' name='username' value="<%=user.getUsername() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Password : <input type='password' name='password' value="<%=user.getPassword() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Add User">
                </td>
            </tr>
        </table>  
</form>
<center>Click <a href="index.jsp"> here </a>to go back.</center>
</body>
</html>