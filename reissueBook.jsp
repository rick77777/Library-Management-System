<%-- 
    Document   : reissueBook
    Created on : 15 May, 2020, 8:54:25 AM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Book"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Re-Issue a Book</title>
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
                <td><h2>Library Management System: Re-Issue a Book</h2>
                </td></tr> 
        </table><br>
<p>
<center style="font-size:13pt;"><b>
        Enter the following details to re-issue a Book<br></b>
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
            SimpleDateFormat dateFormatter = new SimpleDateFormat();

            Date today = new Date();
            String todayString = dateFormatter.format(today);
            //System.out.println(todayString);
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            Date sevenDaysAfterNow = calendar.getTime();
            String sevenDaysAfterNowString = dateFormatter.format(sevenDaysAfterNow);
        %>

<form action='ReIssueBook' method='POST'>
<table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td>Enter User_Name : <input type="text" name="username" style="height:15px;width:150px;font-size:12pt;"  placeholder="Enter User_Name"><br/>
                </td>
            </tr>
            <tr align='center'>
                <td>Enter Book_ID to re-issue : <input type="number" value=0 name="bookid" style="height:15px;width:120px;font-size:12pt;"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Enter new Return Date : <input type="date" name="dateofreturn" style="height:15px;width:130px;font-size:12pt;" placeholder="yyyy-mm-dd"><br/>
                </td>
            </tr> 
            
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Re-Issue Book">
                </td>
            </tr>
        </table>  
    <br>
</form>

<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>