package OnlineStore_Josiah;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class OnlineStoreDAO
 */
@WebServlet("/OnlineStoreDAO")
public class OnlineStoreDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* The following are the fields of Users */
	private Connection connect = null;
	private Statement sm = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ResultSet secondRs = null;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private java.sql.Date birthday;
	private boolean status;

	private static final String setting = "Set SQL_SAFE_UPDATES =0;";
	private static final String setGlobalTime = "SET @@global.time_zone = '+03:00';";
			private static final String setSessionTime =	"SET @@session.time_zone = '+03:00';";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OnlineStoreDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void connect_func() {
		try {
			
			
			if (connect == null || connect.isClosed()) {
				try {
					//set the default timezone to EST 
					TimeZone currentTimeZone = TimeZone.getTimeZone("EST");
					TimeZone.setDefault(currentTimeZone);
					
					Class.forName("com.mysql.cj.jdbc.Driver"); // use old driver for school webserver
				} catch (ClassNotFoundException e) {

				}
				connect = (Connection) DriverManager
						.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?" + "user=john&password=pass1234");
				System.out.println(connect);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void initializeDB() {
		try {
			System.out.print("before func222222222222222222");
			connect_func();
			System.out.print("before111111111111111)");
			Statement statement = connect.createStatement();
			System.out.print("initialize db");

			/*
			 * I take three steps 1. delete all tables if they exist 2. recreate those
			 * tables with specified attributes 3. I insert sample tuples which needed to be
			 * > 10
			 */
			
			final String dropAllTables = "DROP TABLE IF EXISTS Users,favoriteSellers,Categories,favoriteItems,Items,itemsInOrder,itemsInShoppingCart,purchaseOrder, Reviews,shoppingCart ";
			final String Users = "create Table Users(\n" + "	userId varchar(50) not null,\n"
					+ "    passWord varchar(50),\n" + "    firstName varchar(50),\n" + "    lastName varchar(50),\n"
					+ "    email varchar(50),\n" + "    gender char(1),\n" + "    birthday date,\n"
					+ "    currentStatus Boolean,\n" + "    primary key (userId),\n" + "    unique(email) \n" + "); ";

			final String favoriteSellers = "create Table favoriteSellers(\n" + "	userId varchar(50),\n"
					+ "    sellerId varchar(50),\n" + "    primary key(userId, sellerId),\n"
					+ "    foreign key(userId) references Users(userId),\n"
					+ "    foreign key(sellerId) references Users(userId)\n" + ");";

			final String Items = "create Table Items(\n" + 
					" itemid  integer AUTO_INCREMENT,\n" + 
					" sellerId varchar(50),\n" + 
					" title varchar(100),\n" + 
					" descriptions varchar(255),\n" + 
					" price integer,\n" + 
					" Primary key(itemId),"
					+ "foreign key(sellerId) references Users(userid), \n" + 
					" postTime date\n" + 
					" );";

			final String Categories = " create table Categories(\n" + " itemId integer,\n" + " label varchar(50),\n"
					+ " primary key(itemId, label),\n" + " foreign key(itemId) references Items(itemId)\n" + " );\n";

			final String favoriteItems = " create table favoriteItems(\n" + " userId VARCHAR(50),\n"
					+ " itemId Integer not null,\n" + " primary key (userId, itemId),\n"
					+ " foreign key (userId) references Users(userId),\n"
					+ " foreign key (itemId) references Items(itemId)\n" + " );";

			final String Reviews = "create table Reviews(\n" + "reviewId Integer,\n" + "score varchar(50),\n"
					+ "remark varchar(255),\n" + "postTime date,\n" + "userId varchar(50) not null,\n"
					+ "itemId integer not null,\n" + "primary key(reviewId),\n"
					+ "check (score in ('Excellent','Good','Fair','Poor')),\n"
					+ "foreign key (userId) references Users(userId),\n"
					+ "foreign key(itemId) references items(itemId)\n" + ");";
			final String purchaseOrder = "create table purchaseOrder(\n" + "orderId Integer,\n" + "orderTime date,\n"
					+ "customerId varchar(50),\n" + "primary key(orderId),\n"
					+ "foreign key(customerId) references Users(userId)\n" + ");\n";

			final String itemsInOrder = "create table itemsInOrder(\n" + "itemId Integer,\n" + "orderId Integer,\n"
					+ "foreign key(itemId) references items(itemId),\n"
					+ "foreign key(orderId) references purchaseOrder(orderId),\n" + "primary key(orderId)\n" + ");";
			final String shoppingCart = "create table shoppingCart (\n" + "shoppingCartId Integer,\n"
					+ "customerId varchar(50),\n" + "foreign key(customerId) references Users(userid),\n"
					+ "primary key(shoppingCartId)\n" + ");";
			final String itemsInShoppingCart = "create table itemsInShoppingCart(\n" + "itemId Integer,\n"
					+ "shoppingCartId Integer,\n"
					+ "foreign key(shoppingCartId) references shoppingCart (shoppingCartId),\n"
					+ "primary key(shoppingCartId)\n" + "\n" + "); ";
			final String noMoreThanFiveReviewsPerDay = "create trigger noMoreThanFiveReviewsPerDay before insert on Reviews\n"
					+ "for each row \n" + "	begin \n"
					+ "    if( select Count(*) from Review R where R.userId = New.userId AND R.postTime = curdate()) >= 5\n"
					+ "    then \n" + "    SIGNAL SQLSTATE '45000'\n"
					+ "    set MESSAGE_TEXT ='Cannot post more than five reviews per day';\n" + "    \n"
					+ "    end if;\n" + "    end;\n";
			final String noMoreThanFiveItemsPerDay = " create  trigger  noMoreThanFiveItemsPerDay before insert on Items for each row \n"
					+ "				    begin\n"
					+ "				    if (select COUNT(*) from Items I where I.sellerId = NEW.sellerId and  I.postTime = curdate() group by I.sellerId ,new.postTime) >=5 then \n"
					+ "				       SIGNAL SQLSTATE '45000'\n"
					+ "                       set message_text = \"Cannot post more than five items per day\";\n"
					+ "                    end if; \n" + "                    end; ";

			final String onlyOneReviewOnSameItem = "create trigger onlyOneReviewOnSameItem before insert on Reviews for each row\n"
					+ "				begin \n"
					+ "                if(select count(*) from Review R where R.userId  = New.userId AND R.itemId = New.itemId) >= 1 then \n"
					+ "                SIGNAL SQLSTATE '45000'\n"
					+ "                set message_text = \"You cannot write more than one review to the same item\";\n"
					+ "                end if;\n" + "                end; ";
			
			final String initializeUserTable = "insert into Users values('Dave','pass1234','Dave','Liu','daveliu@gmail.com','M','2000-02-02',1),('JamesHarden','111','James','Harden','JamesHarden@gmail.com','M','2000-02-02',1),\n" + 
					"('LBJ','pass1234','Lebron','James','LBJ@gmail.com','M','2000-02-02',1),('Kb','111','Kobe','Bryant','KB22@gmail.com','M','2000-02-02',1),('LeonC','111','Leon','Chou','LeonChou@gmail.com','M','2000-02-02',1),\n" + 
					"('Ming','111','Ming','Jin','Jin@gmail.com','M','2000-02-02',1),('Jacob','111','Jacob','Billy','Jb@gmail.com','M','2000-02-02',1),\n" + 
					"('Wen','111','Wendy','Tung','wen@gmail.com','M','2000-02-02',1),('Patrick','111','Patrick','Chu','techlead@gmail.com','M','2000-02-02',1),\n" + 
					"('Jonah','111','Jonah','Wilber','JWb0@gmail.com','M','2000-02-02',1),('Jonathan','111','Jonathan','Ma','Joma@gmail.com','M','2000-02-02',1);";
			final String insertItem = "insert into  Items (sellerId,  title,   descriptions, price, postTime) values('Ming',\n" + 
					" 'ok', 'desc lll', 12, '2019-01-01');";
			final String anotherItem = "insert into Items (sellerId,title) values('Wen','Phone');";
			final String insertCat = "insert into Categories values(1,'electronics'),(1,'apple');";
			
			String[] stmt = { setting,setGlobalTime,setSessionTime,dropAllTables,Users, favoriteSellers, Items,Categories, favoriteItems, Reviews, purchaseOrder,itemsInOrder,
					shoppingCart, itemsInShoppingCart};
			for (int i = 0; i < stmt.length; i++) {
		
				statement.executeUpdate(stmt[i]);
				System.out.print("-----" + stmt[i]);
				// Use execute update because i dont need resultset here. I will list all tuples
				// using listAllUsers() later.
				// ps.close();
			}
			String[] triggers = { noMoreThanFiveReviewsPerDay, noMoreThanFiveItemsPerDay, onlyOneReviewOnSameItem };
			for (int j = 0; j < triggers.length; j++) {
				statement.execute(triggers[j]);
			}
			
			String [] insertStatement = {
					initializeUserTable,insertItem,anotherItem,insertCat
			};
			//I deliberately insert statments after trigger to make sure the
			//the queries in triggers are functioning well. 
			for(int k = 0; k < insertStatement.length; k++) {
				statement.execute(insertStatement[k]);
			}
			// here comes a thing. If the queries ahead of triggers have some integrity
			// violation, then the rest of queries will not be executed
			// this is why I have faced some problems creating triggers. Because the insert
			// statement prior to trigger statement encountered
			// some problems.
			statement.close();
			connect.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.print("Josiah, You have failed to initialized your database"); // check statement.

			e.printStackTrace();
		}

	}

	public List<Users> getUserList() throws SQLException {
		connect_func();
		System.out.println("getUserList");
		List<Users> listUsers = new ArrayList<Users>();
		String sql = "Select * from Users;";
		System.out.println("Catch error createsetatement 1111111111111111");
		sm = (Statement) connect.createStatement();
		System.out.println("Catch error createstsatement 2222222222222222);"
				);
		ResultSet rs = sm.executeQuery(sql);

		while (rs.next()) {
			userId = rs.getString("userId");
			password = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			 birthday = rs.getDate("birthday");
			 email = rs.getString("email");
			 gender = rs.getString("gender");
			status = rs.getBoolean("currentStatus");			

			
			// use this to make sure the
																							// data
			// have been read from the database;
			Users newUser = Users.createUserInDAO(userId, password, firstName, lastName, email, status, birthday, gender);
			listUsers.add(newUser);
		}
		rs.close();
		sm.close();
		connect.close();
		
		System.out.println( "return listUsers -----------------------------");
		return listUsers;
	
	
			
		
	}

	
/*getUser is used when identify a user in login.*/
	public Users getUser(String userId, String password) throws SQLException {
		
		List<Users> listUsers = getUserList();
		
		for(Users theUser: listUsers) {
		System.out.println(theUser.getUserId() + theUser.getPassword());
			if(theUser.getUserId().equals(userId) && theUser.getPassword().equals(password)) {
				return theUser;
			}
			
		}
		return null;
		
}
	
	public boolean signup(Users newUser) throws SQLException {
		connect_func();
		// before we insert the user information into the database, let's check whether
		// the userName already
		// exists.
		String InsertUsers = "INSERT INTO Users values (?,?,?,?,?,?,?,?)";

		// Use execute update because i dont need resultset here. I will list all tuples
		// using listAllUsers() later.
		// ps.close();

		ps = (PreparedStatement) connect.prepareStatement(InsertUsers);
		ps.setString(1, newUser.getUserId());// use account as index
		ps.setString(2,	newUser.getPassword());
		ps.setString(3, newUser.getFirstName());
		ps.setString(4, newUser.getLastName());
		ps.setString(5, newUser.getEmail());
		ps.setString(6, newUser.getGender());
		ps.setDate(7, newUser.getBirthday());
		ps.setBoolean(8, true);
		ps.executeUpdate(); // we dont need to return any value after we successfully
		// create a new user tuple, we need to direct him or her to the homepage.
		ps.close();
		connect.close();

		return true;

	}
	
	
	public ArrayList<Items> getAllItems() throws SQLException {
		ArrayList<Items> allItems = new ArrayList<>();
		connect_func();
		System.out.print("In getALlItems in dao");
		String getItems =
				"Select * from Items I, Categories C where I.itemId = C.itemId;" ;		
		ps = connect.prepareStatement(getItems);
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int itemId = rs.getInt("itemId");
			String title = rs.getString("title");
			String descriptions = rs.getString("descriptions");
			int price = rs.getInt("price");
			String sellerId = rs.getString("sellerId");
			java.sql.Date postTime = rs.getDate("postTime");
			System.out.print(1 + " of Retrieve Item with cat");
			String category = rs.getString("label");
			Items theItem = Items.createItemsInDAO(itemId, sellerId, title, descriptions, price, postTime,category);
			allItems.add(theItem);
			System.out.println(theItem.getCategory() + "The name the item");
		}
		
		rs.close();
		ps.close();
		connect.close();
		
		System.out.println("Finish getAll items");
		return allItems;
	}
	
	

	public ArrayList<Items> searchItems(String category) throws SQLException {
		ArrayList<Items> allItems = new ArrayList<>();
		connect_func();
		System.out.print("In searchItem in dao");
		String getItems =
				"Select * from Items I, Categories C where I.itemId = C.itemId And C.label = ?;" ;		
		ps = connect.prepareStatement(getItems);
		ps.setString(1, category);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int itemId = rs.getInt("itemId");
			String title = rs.getString("title");
			String descriptions = rs.getString("descriptions");
			int price = rs.getInt("price");
			String sellerId = rs.getString("sellerId");
			java.sql.Date postTime = rs.getDate("postTime");
			System.out.print(1 + " of Retrieve Item with cat");
			Items theItem = Items.createItemsInDAO(itemId, sellerId, title, descriptions, price, postTime,category);
			allItems.add(theItem);
			System.out.println(theItem.getCategory() + "The name the item");
		}
		
		rs.close();
		ps.close();
		connect.close();
		
		System.out.println("Finish searching items");
		return allItems;
	}
	
	
}
