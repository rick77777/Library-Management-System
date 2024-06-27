<%-- 
    Document   : addBook
    Created on : 25 Apr, 2020, 3:41:27 PM
    Author     : CHIRADIP
--%>

<%@page import="Library.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: Add a new Book</title>
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
                <td><h2>Library Management System: Add a new Book</h2>
                </td></tr> 
        </table><br>
<p>
<center>
This form allows you to add a new Book.
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

<form action='NewBook' method='POST'>
<table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td>Book ID : <input type='text' name='id' value="<%=book.getBookId()%>"> <br/>
                </td>
            </tr>
            <tr align='center'>
                <td>Book Name : <input type='text'name='bkname' value="<%=book.getBookName()%>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Author Name : <input type='text' name='aname' value="<%=book.getAuthorName()%>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>ISBN : <input type='text' name='isbn' value="<%=book.getISBN() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Publisher : <input type='text' name='pub' value="<%=book.getPublisher() %>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td>Subject : <input type='text' name='sub' value="<%=book.getSubject() %>"><br/>
                </td>
            </tr>
            <tr align='center'>
                <td>Year of Publication : <input type='text' name='year' value="<%=book.getYear() %>"><br/>
                </td>
            </tr>
            <tr align='center'>
                <td>Total Copies : <input type='text' name='totcopy' value="<%=book.getTotalCopies()%>"><br/>
                </td>
            </tr> 
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Add Book">
                </td>
            </tr>
        </table>  
</form>
<br><br>
<center>Click <a href="adminsection.jsp">here</a> to go back to the admin page.</center><br>
<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>
