<%-- 
    Document   : searchBook
    Created on : 14 May, 2020, 12:10:46 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Search for a Book</title>
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
                <td><h2>Library Management System: Search Book Details</h2>
                </td></tr> 
        </table><br>
<p>
<center style="font-size:13pt;"><b>
        Select an option to cater your search to<br></b>
</center>
</p>

<%
        Book book = new Book();
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
		book = (Book)request.getAttribute("book");	
	}
%>

<div class="content3">
<form action='SearchBook' method='POST'>
 
    <input type="radio" name="menuselection" value="bookname"><label style="font-size:21px" >Book Name </label><br><br>
    <input type="radio" name="menuselection" value="authorname"><label style="font-size:21px" >Author Name </label><br><br>
    <input type="radio" name="menuselection" value="subjectname"><label style="font-size:21px" >Subject Category </label><br><br>
    <input type="radio" name="menuselection" value="yearpub"><label style="font-size:21px" >Year of Publication </label><br><br><br><br>
    <center>
        <input type="text" name="query" style="height:25px;width:220px;font-size:13pt;"  placeholder="Enter a detail to Search"><br><br>
    </center>
    <table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Submit">
                </td>
            </tr>
        </table>  

</form>
</div>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>