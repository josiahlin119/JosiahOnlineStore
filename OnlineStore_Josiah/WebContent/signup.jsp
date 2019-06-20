<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Josiah's online shopping store registration page</title>
</head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text],input[type=email], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color:  #FFD700;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
</style>
</head>
<body background="/OnlineStore_Josiah/resources/ecommerce.jpg">
	<div style="width: 800px; margin: 0 auto;">
		
				<% String firstName = request.getParameter("firstName"); 
				if(firstName == null) {
					firstName = " ";
				}
				String lastName = request.getParameter("lastName");
				if(lastName == null){
					lastName = " ";
				}
				String email = request.getParameter("email");
				if(email == null){
					email = " ";
				}
				
			
				%>
<form action="OnlineStoreControlServlet" method="post">
							<input type="hidden" name="action" value="signup" required/>
			<div class="container">
				<h1>Registration form</h1>
				<p><font color = "lightblue">Become a Josiah's Shopping Store member today</font></p>
				<hr>
				<label for="UserID"><b>User ID</b></label> <input type="text"
					placeholder= "User Id" name="userId"  required  maxlength="30" size="30">
				<label for="email"><b>Email</b></label>
				 <input type="email"
					placeholder="Make sure you enter a validated email" name="email" value = "<%=email%>"  required > 
					
					<label
					for="password"><b>Password</b></label> 
					<input type="password"
					placeholder="New Password" name="password"  required> 
					
					
					<label
					for="passwordAgain"><b>Password Again</b></label> <input
					type="password" placeholder="..." name="passwordAgain"
					
					required> 
					
					<label for="firstName"><b>First Name</b>
					
					</label>
				<input type="text" placeholder="Enter Your First Name"
					name="firstName" value = "<%=firstName%>"  required> 
					
					<label for="lastName"><b>Last
						Name</b></label> <input type="text" placeholder="Enter Your Last Name"
					name="lastName" value = "<%=lastName%>"  required>
					
						<label for="lastName"><b>Birthday</b></label> <input type="date"
					placeholder="Day of Birth" name="birthday" value = <%=request.getParameter("birthday") %>  required>




				<hr>
<!-- Gender -->
<label for="gender"><b>Gender</b></label>


				<%
					String gender = request.getParameter("gender");
					if (gender != null && gender.equals("M")) { // if the default value is M
				%>


				<input type="radio" name="gender" checked value="M" /> M


				<%
					} else {
				%>

				<input type="radio" name="gender" value="M" /> M

				<%
					}
					;

					if (gender != null && gender.equals("F")) { // if the default value is F
				%>

				<input type="radio" name="gender" checked value="F" /> F <br />
				<br />



				<%
					} else {
				%>

				<input type="radio" name="gender" value="F" /> F <br /> <br />

				<%
					}
					;
				%>
				
				<!-- End of gender -->
				

			
			

				<button type="submit" class="registerbtn">Register</button>
			</div>

			<div class="container signin">
				<p>
					Already have an account? <a href="login.jsp">Sign in</a>.
				</p>


			</div>
		</form>
	</div>
</body>
</html>

