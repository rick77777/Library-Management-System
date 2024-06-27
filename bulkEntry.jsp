<%-- 
    Document   : bulkEntry
    Created on : 29 May, 2020, 9:54:49 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Bulk Entry</title>
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
                <td><h2>Library Management System: Bulk Entry (Book/User)</h2>
                </td></tr> 
        </table><br>
<p>
<center style="font-size:13pt;"><b>
        Select an option to cater your entry to<br></b>
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

<div class="content3">
<form action='BulkEntryServlet' method='POST' enctype = "multipart/form-data">
 <center>
    <input type="radio" name="menuselection" value="book"><label style="font-size:21px" >Book Entry</label><br><br>
    <input type="radio" name="menuselection" value="user"><label style="font-size:21px" >User Entry</label><br><br><br>
    
        <input type = "file" name = "filex" size = "60" style="margin-left:50px;height:20px;" /><br><br>
    </center>
    <table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Upload File">
                </td>
            </tr>
        </table>  

</form>
</div>
<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>                
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>