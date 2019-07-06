<%@ page import="java.util.*, OnlineStore_Josiah.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>


<head>
<link type="text/css" rel="stylesheet" href="resources/css/style.css">

<title>Josiah's Web Management System</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

th, td {
  padding:20px;
  text-align: left;
  border: 1px solid purple;
}

body {
	background-color: #b3eeec;
	font-family: cursive;
}

.glow {
	font-size: 80px;
	color: #fff;
	text-align: center;
	-webkit-animation: glow 1s ease-in-out infinite alternate;
	-moz-animation: glow 1s ease-in-out infinite alternate;
	animation: glow 1s ease-in-out infinite alternate;
}

@
-webkit-keyframes glow {from { text-shadow:0010px#fff, 0020px#fff,
	0030px#e60073, 0040px#e60073, 0050px#e60073, 0060px#e60073, 0070px#e60073;
	
}

to {
	text-shadow: 0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px
		#ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
}
}
</style>
<h2 class="glow">Josiah's User Management System</h2>
<c:url var="logoutLink" value="OnlineStoreControlServlet">
	<c:param name="action" value="logout" />
</c:url>
<!-- Use this url to ban the certain user -->
<nav class="cl-effect-13">
	<div id="right">
		<a href="${logoutLink }"> Log Out</a>
	</div>
</nav>
<style>
a:link, a:visited {
	background-color: LightSkyBlue;
	color: SpringGreen;
	padding: 15px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

.link1 {
	background-color: Red;
	color: SpringGreen;
	padding: 15px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

a:hover, a:active {
	background-color: DarkCyan;
}
</style>
</head>
<body>
	<div align="center">

		<div id="wrapper">
			<div id="header">
				<div id="container">
					<div id="content">
					
					<form>
					<br/><br/>
					<br/><br/>
						<table>
							<tr>
								<th>Name</th>
								<th>User Id</th>
								<th>email</th>
								<th>Gender</th>
							
							</tr>
							<c:forEach var="temp" items="${USER_LIST}">
								<tr>
									<td>${temp.firstName}${temp.lastName}</td>
									<td>${temp.userId}</td>
									<td>${temp.email}</td>
									<td>${temp.gender}</td>
						
							</c:forEach>
						</table>
						
					
						</form>
	<c:url var="tempLink" value="OnlineStoreControlServlet">
							<c:param name="action" value="initialize" />
						</c:url>
						
					<br/><br/><br/><br/>
						<nav class="cl-effect-17">
							<a href="DataManagement.jsp"> Data </a> <a href="${tempLink}">Initialize</a>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>