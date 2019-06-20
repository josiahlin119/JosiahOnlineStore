package OnlineStore_Josiah;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
class Administrator extends Users{

	protected Administrator(String userId, String password, String firstName, String lastName, String email,
			boolean status, String birthday, String gender) {
		super(userId, password, firstName, lastName, email, status, birthday, gender);
		// TODO Auto-generated constructor stub
	}


	protected static String userId = "root";
	protected static String birthday = "2000-02-12";
	protected static String password = "pass1234"
;
	protected static String firstName = "Josiah";
	protected static String lastName = "Lin";
	protected static String gender = "Male";
	public static Administrator createAdmin() {
		return new Administrator(userId,password,firstName,lastName," ",true,birthday,gender);
	}  //creation method
	
	
	
};
public class Users {
	
	//this is the id that is automatically created when creating the user to help mysql to do searching. 
	
	protected String userId,password, firstName, lastName, address,email;
	protected String fullName;
	protected java.sql.Date birthday;
	protected boolean status = true; /*to check if the existed user is banned
	or not. only the administer has the authority to check this status. 
	*/
	//protected ArrayList<String>favoriteJokes = new ArrayList();
	protected boolean exist;   //this is to check if the user exists. 

	private String gender;

	private int numberOfJokes;
	
	
	private static java.sql.Date convertJavaDateToSQLdate(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	private static java.sql.Date convertStringToDate(String birthday) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			Date formattedBirthday = format.parse(birthday);
			return convertJavaDateToSQLdate(formattedBirthday);
		}
		
		 catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
//This is used to create a new user.   

	//this is a contructor to build a friend instance; 
	
	
	protected Users(String userId, String password, String firstName, String lastName,
			String email, boolean status, String birthday, String gender) {
		this.userId = userId; // id is unique and created by user himself
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setGender(gender);
		this.status = status;
		this.birthday = convertStringToDate(birthday); //convert string birthday from client to sql.date object
	}
	
	protected Users(String userId, String password, String firstName, String lastName,
			String email, boolean status, java.sql.Date birthday, String gender) {
		
		this.setUserId(userId);// id is unique and created by user himself
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setGender(gender);
		this.status = status;
		this.birthday = birthday; //convert string birthday from client to sql.date object
	}

	
	public static Users createUserInDAO(String userId, String password, String firstName, String lastName,
			String email, boolean status, java.sql.Date birthday, String gender) {
		
		
		return new Users(userId,password,firstName,lastName,email,status,birthday,gender);
	}
	
	public static Users createUserInSignUp(String userId, String password, String firstName, String lastName,
			String email, boolean status, String birthday, String gender) {
				return new Users( userId,  password,  firstName,  lastName,
			 email,  status,  birthday,  gender);
		
	}
	
	
	protected Users getFriend(String friendUserId, String firstName,String lastName) {
		return new Users(friendUserId," ",firstName,lastName," ",true,"2009-12-31"," ");
		
		
	}
	
	
	
	
	
	
	 // Use the below one when DAO is trying to pull all user info out of database and pass
 // this user object to the Servlet.
	

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		fullName = firstName + " " + lastName;
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}
	
	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public boolean isAccess() {
		return status;
	}
	public boolean isExist() {
		if(userId !=null || password !=null) {
		
		return true;
		}
		else {
			return false;
		}
	}
	


	public void setStatus(boolean status) {
		this.status = status;
	}
public boolean getStatus() {
	return status;
}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	private void setEmail(String email2) {
		this.email = email2;
		
	}

	public int getNumberOfJokes() {
		return numberOfJokes;
	}

	public void setNumberOfJokes(int numberOfJokes) {
		this.numberOfJokes = numberOfJokes;
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
}
