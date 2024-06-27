package Library.model;

/**
 *
 * @author CHIRADIP
 */
public class User {

	private String firstName=" ";
	private String surname="";
	private int age=0;
	private String gender="";
        private String email="";
	private String username="";
	private String password="";
	
	public User(String firstName, String surname, int age,String gender,String email,String username,String password){
		this.firstName=firstName;
		this.surname=surname;
		this.age=age;
		this.gender=gender;
                this.email=email;
		this.username=username;
		this.password=password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public String getGender()  {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
        public String getEmail()  {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password ;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}