
<%@ page import="java.util.*,OnlineStore_Josiah.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<meta charset="UTF-8">
<title>Josiah's Joke Hub</title>

<link type="text/css" rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href='resources/css/searchBar.css'>
<link rel="stylesheet" href='resources/css/component.css'>
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Nunito'>


</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  background-color: pink;
  font-family: cursive;
}

.glow {
  font-size: 50px;
  color:#FFD700;
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #40E0D0, 0 0 40px #40E0D0, 0 0 50px #40E0D0, 0 0 60px #40E0D0, 0 0 80px #40E0D0;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px #00CED1, 0 0 40px #00CED1, 0 0 50px #00CED1, 0 0 60px #00CED1, 0 0 70px #00CED1, 0 0 90px #00CED1;
  }
}
</style>

<h2 >
<font face="verdana" color="mediumblue">
	<%
		String account = request.getSession().getAttribute("firstName").toString()+ " "+  request.getSession().getAttribute("lastName").toString();
		out.println("Hello, " + account);
	%>
</font>
</h2>



<h2 class="glow">Josiah's Online Store </h2>



<c:url var="logoutLink" value="OnlineStoreControlServlet">
	<c:param name="action" value="logout" />
</c:url>
<!-- Use this url to ban the certain user -->
<nav class="cl-effect-13">
	<div id="right">
		<a href="${logoutLink }"> Log Out</a>
	</div>
</nav>

<body>

	<c:url var="loadFavSellersLink" value="OnlineStoreControlServlet">
		<c:param name="action" value="loadFriends" />
	</c:url>

	<c:url var="loadFavItemsLink" value="OnlineStoreControlServlet">
		<c:param name="action" value="loadJokes" />
	</c:url>

	<nav class="cl-effect-1">

		<a href="postItems.jsp"> Post New Items</a> 
		<a
			href="${loadFavSellersLink}">Favorite Sellers</a> 
			
			<a href="${loadFavItemsLink}">
			My Favorite Items</a>
<a href="homepage.jsp">Home</a>

	
	
	</body>
	</html>