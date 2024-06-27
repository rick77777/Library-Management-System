package Library.model;

public class Reserve {

	private int transactionId=0;    
        private String userName="";
	private int bookId=0;
        
	
	public Reserve (int transactionId,String userName,int bookId){
		this.transactionId=transactionId;
		this.userName=userName;
		this.bookId=bookId;
	}
	
	public Reserve() {
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
}