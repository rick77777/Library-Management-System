<%-- 
    Document   : calcFine
    Created on : 16 May, 2020, 8:39:24 AM
    Author     : CHIRADIP
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Calculate Fine</title>
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
.content3 {
              max-width: 18%;
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
                <td><h2>Library Management System: Calculate Fine</h2>
                </td></tr> 
        </table><br>
<p>
<center style="font-size:13pt;"><b>
        Please enter the following Details to calculate Fine <br></b>
</center>
</p>

<%
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
	}
%>
                
<%
	if(request.getAttribute("fine") != null){
	
		java.util.List<String> fine = (java.util.List<String>)request.getAttribute("fine");
		for(String fn: fine){	
%>
		<li> <%= fn%></li>		
<%		}
	}
%>

<div class="content3">
<form action='calculateFine' method='POST'>
 
    <center>
        <input type="text" name="username" style="height:25px;width:220px;font-size:13pt;"  placeholder="Enter User Name"><br><br>
    </center>
    <center>
        <input type="number" name="bookid" style="height:25px;width:220px;font-size:13pt;"  placeholder="Enter Book ID"><br><br>
    </center>
    <table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Submit">
                </td>
            </tr>
        </table>  

</form>
</div>
<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>