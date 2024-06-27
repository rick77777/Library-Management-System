# Library-Management-System
Library Management System using PhP, JDBC and MySQL



This is an interactive Library Management System.
It consists of the basic sections - 
1) Visitor Home Page where the books available are displayed
2) Log-In Function
3) User Registration Function
4) Admin Login
5) Add New Books
6) Edit Book Details
7) List all the Books Available (Admin only)
8) List the Borrowed Books and the Borrowers
9) List all the Users of the System
10) Handle User Accounts (Create/Delete Accounts)
11) Checkout Books
12) Return Books
13) Calculate Fines
14) Re-Issue a Borrowed Book
15) Edit User Details (can be done only by user)
16) Search catalog for a book (can be done only by user)

[ Please Read the N.B. portion before proceeding ]

Setting up the system - 

1) Download and install NetBeans IDE.

2) Create a new Web application named 'LibraryWebApp' in it from the projects tab.

3) Copy and Paste the codes of the JSP files after creating JSP files and naming them exactly as the ones from which the codes are being copied respectively from the JSP Files folder.

4) Create a folder named Library under which all the servlet files are to be placed with the exact same names as the given file names as present under the JAVA src files Folder.

5) Another folder named 'model' is to created under the 'Library' folder and three files are to be created as per the image 'System.png'.

6) Under Configuration Files folder and the WEB-INF folder, there is a web.xml file, where the contents of the web.xml file provided will be pasted. Both must have the exact same contents as the 'web.xml' file provided. Also right click on the in-built Libraries folder and add the new Library 'MySQL JDBC Driver'.

7) Right click on the web application folder and click 'Clean and Build' after saving all the contents as required. The project tab should have a hierarchy as displayed in the figure 'System.png'.

8) Now form the Services tab connect to the database using options provided in 'Connection.png' and create a new database named library.

9) The SQL code is provided to create 3 tables under the library database. Copy them and execute them.

10) Again Right click on the web application folder and click 'Run', after which the GlassFish Server would be run and the Home Page will be displayed.

11) Fine is calculated as follows - 
	First 14 days after return date - Rs. 2 per day
	Next 14 days after return date - Rs. 3 per day
	After the total 28 days after retun day - Rs. 5 per day

12) Maximum Limit for Issuing or Re-Issuing (Renewing) of books is 10 days and at most 5 books can be borrowed by a user at a given time.


N.B. - 


The interface is self explanatory.

All the functionalities are fully implemented.

Many of the bugs(mostly unscrupulous usage bugs) that persisted in Part 1, are dealt with in this Part 2 enhancements.

Books have 'id', Users have 'username' and Checkout has 'transaction_id' as their respective primary keys.

Deletion of books can be done using the edit books option from the admin portion.

Admin has username - admin and password - admin.

If the setup is errorneous refer to the 'System' folder and setup up your system exactly as it is in a NetBeans Project.
