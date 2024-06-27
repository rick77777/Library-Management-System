<%-- 
    Document   : index
    Created on : 25 Apr, 2020, 7:39:42 AM
    Author     : CHIRADIP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .buttonx {
              background-color: #a64dff;
              border: none;
              color: white;
              padding: 15px;
              text-align: center;
              text-decoration: none;
              display: inline-block;
              font-size: 15px;
              margin: 4px;
              cursor: pointer;
              border-radius: 12px;
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

            .content {
              max-width: 100%;
              margin: auto;
              padding: 20px;
            }

            .content2 {
              max-width: 30%;
              margin: auto;
              padding: 20px;
            }
            
            .content3 {
              max-width: 14%;
              margin: auto;
              padding: 20px;
            }
            img {
              display: block;
              margin-left: -30px;
              margin-right: auto;
              width:120%;
            }
        </style>
        <title>LIBRARY WEB APP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <table class="content" border='1' cellpadding='5' cellspacing='0' width='800'>
            <tr bgcolor='#cab3ff' align='center'>
                <td><h2>WELCOME TO THE LIBRARY MANAGEMENT SYSTEM</h2>
                </td></tr> 
        </table>
        
        <h3 class='content2'>This is the home page for Library Management System.</h3>

  

        <div class="content3">
         <a href='visitorHomePage.jsp' class="buttonx">VISITOR'S HOME PAGE</a><br><br>
        </div>
        
        <form action="GreetingServletPath" method="POST">
            <table class="content" cellpadding='5' cellspacing='10' width='400'>
            <tr align='center'>
                <td>User Name : <input type="text" name="userName" size="25"><br>
                </td>
            </tr>
            <tr align='center'>
                <td>Password : <input type="password" name="password" size="20">
                </td>
            </tr> 
            <tr align='center'>
                <td><input class='buttony' type="submit" value="Sign in">
                </td>
            </tr>
        </table>  
        </form>
        <p style='max-width:18%;margin: auto;padding: 20px;'> Not registered yet? Click <a href="addUser.jsp" >here</a> to register.<br><br>
        <img src="https://i2.wp.com/www.rhomeguesthouse.com/wp/wp-content/uploads/2019/01/DiaryHeroArticle_Places-Angelica_001_Default.jpg?fit=750%2C381&ssl=1">
    </body>
</html>
