package Library.model;

import java.util.Date;

public class CheckOut {

	private int transactionId=0;
    
        private String userName="";
	private int bookId=0;
        private Date returnDate = null;

	
	
	public CheckOut (int transactionId,String userName,int bookId, Date returnDate){
		this.transactionId=transactionId;

		this.userName=userName;
		this.bookId=bookId;
                this.returnDate = returnDate;	
	}
	
	public CheckOut() {
		// TODO Auto-generated constructor stub
	}
	public int getTransactionId()  {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId=transactionId;
	}
	
	public String getUserName()  {
		return userName;
	}
	public void setUserName(String userName)  {
		this.userName=userName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }
}