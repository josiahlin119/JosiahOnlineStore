package OnlineStore_Josiah;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import OnlineStore_Josiah.Users;

/**
 * Servlet implementation class OnlineStoreControlServlet
 */
@WebServlet("/OnlineStoreControlServlet")
public class OnlineStoreControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineStoreDAO onlineStoreDao;
	private boolean successfullyRegistered;  
	
	public void init() {
		onlineStoreDao = new OnlineStoreDAO();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineStoreControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		response.setContentType("text/html");
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		switch(action) {
		case"login":
			try {
				login(request,response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "signup":
			try {
				signup(request,response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "logout":
			logout(request,response);
			break;
		case "initialize":
			try {
				initialize(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordAgain = request.getParameter("passwordAgain");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		
		Users newUser = Users.createUserInSignUp(userId, password, firstName, lastName, email, true, birthday, gender);
//		boolean isFieldEmpty =	checkNullPointer(allAttributes);
//			if(isFieldEmpty) {
//				response.sendRedirect("guest-signup.jsp");
//			}
		/*
		 * Note: registration is the opposite of sign in. If the account already exists,
		 * we should warn the user that the account cannot be created again
		 */
		if (!(password.equals(passwordAgain))) {
			request.setAttribute("unmatchedPassword", 1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
			dispatcher.forward(request, response);
		}
		
		Users user = onlineStoreDao.getUser(userId, password);
		
		if (user != null) {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
			dispatcher.forward(request, response);
		} 
	
			successfullyRegistered = onlineStoreDao.signup(newUser);
			// create a new user tuple, we need to direct him or her to the homepage.
			if (successfullyRegistered) {
				Users theUser = onlineStoreDao.getUser(userId, password);
				System.out.print(theUser.getEmail());
				request.setAttribute("successfullyRegistered", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

		
		}
		
	

	

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
	
		
		System.out.println("Login method");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// if the the user is "root", then direct him to
		// Administrator.jsp which has been embedded in listUsers();
		// out.println("This is login"); check admin before we check common users;
		
		
		/*Here is a check block for admin*/
		Administrator Admin = Administrator.createAdmin();
		System.out.print("Print Admin name" + Admin.getFirstName() + Admin.getLastName() + Admin.getUserId() + Admin.getPassword());
		if(userId.contentEquals(Admin.getUserId())&& password.equals(Admin.getPassword())) {
			System.out.print("This is Administrator");
				listUsers(request,response);
		}
		
		
		System.out.println("before call getUser aaaaaaaaaaaaaaaaaaaa");

		Users user = onlineStoreDao.getUser(userId, password);
		
		System.out.println("after call getUser bbbbbbbbbbbbbbbbbbbb");

		
		if (user == null) { // case 1: there is no such user
			response.sendRedirect("login.jsp");
		}

		else if (user.isAccess()) { // case 2: the user exists, and she can access it
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("firstName", user.getFirstName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
//			
		} else { // case 3: the user exists, but she cannot access it
			response.sendRedirect("login.jsp");
			
		}          
		
	}
	
	
	protected void initialize(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("Call initializeDB in COntrol servelt");
		onlineStoreDao.initializeDB();
		System.out.print("finish calling initializeDB");
		listUsers(request, response);
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException
			{
		List<Users> allUsers = onlineStoreDao.getUserList();
		HttpSession session = request.getSession(true);
		session.setAttribute("id", 10000);
		session.setAttribute("USER_LIST", allUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administrator.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
}

