<%-- 
    Document   : adminsection
    Created on : 25 Apr, 2020, 11:13:11 AM
    Author     : CHIRADIP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style>
            .buttony {
              background-color: #a64dff;
              border: none;
              color: white;
              padding: 10px;
              text-align: center;
              text-decoration: none;
              display: inline-block;
              font-size: 16px;
              margin: 1px;
              cursor: pointer;
              border-radius: 5px;
            }

            .content {
              max-width: 100%;
              margin: auto;
              padding: 20px;
            }
            
            .content3 {
              margin: auto;
              margin-left: 635px;
              padding: 20px;
            }
            img {
              display: block;
              margin-left: -30px;
              margin-right: auto;
              width:120%;
            }
        </style>
</head>
<body bgcolor='white'>

        <table class="content" border='1' cellpadding='5' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>Welcome to Admin Section Page</h2>
                </td></tr> 
        </table>

<h3>
<center>
This is the page for Admin Section.
</center>
</h3> 
    
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


<form action="AdminSection" method="post">
    <div class="content3">
    <a href='addBook.jsp' style="margin-left: 40px;"><label style="font-size:21px" >Add a New Book </label></a><br><br>
    <a href='addordeluser.jsp' style="margin-left: 5px;"><label style="font-size:21px" >Handle Single User Account</label></a><br><br>
    <a href='bulkEntry.jsp'><label style="font-size:21px" >Handle Bulk Entry (Book/User)</label></a><br><br>

    <input type="radio" name="menuselection" value="listbooks"><label style="font-size:21px" >List Books </label><br><br>
    <input type="radio" name="menuselection" value="listborrowedbooks"><label style="font-size:21px" >List Borrowed Books </label><br><br>
    <input type="radio" name="menuselection" value="listusers"><label style="font-size:21px" >List Users </label><br><br>
    <input type="radio" name="menuselection" value="checkoutbook"><label style="font-size:21px" >Checkout/Return/Reserve Book </label><br><br>
    
    <a href='editBook.jsp' style="margin-left: 30px;"><label style="font-size:21px" >Edit a Book Detail</label></a><br><br>
    <a href='reissueBook.jsp' style="margin-left: 40px;"><label style="font-size:21px" >Re-issue a Book</label></a><br><br>
    <a href='calcFine.jsp' style="margin-left: 45px;"><label style="font-size:21px" >Calculate Fine</label></a><br>
    
    </div>
    <table class="content" width='400'>
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Submit">
                </td>
            </tr>
    </table>  

<center>Click <a href="index.jsp">here</a> to log out.</center>
</form> 
</body>
</html>