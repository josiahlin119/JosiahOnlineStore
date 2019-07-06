
<%@ page import="java.util.*,OnlineStore_Josiah.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<meta charset="UTF-8">
<title>Josiah's Online Store</title>


<link type="text/css" rel="stylesheet" href="resources/css/topNav.css">

</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  background-color: lemonchiffon;
  font-family: cursive;
  font-family: Arial, Helvetica, sans-serif;
}

.glow {
  font-size: 50px;
  color:seashell;
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px palegreen, 0 0 40px palegreen, 0 0 50px palegreen, 0 0 60px palegreen, 0 0 80px palegreen;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px lime, 0 0 40px lime, 0 0 50px lime, 0 0 60px lime, 0 0 70px lime, 0 0 90px lime;
  }
}
</style>
<body>
<h2 >
<font face="verdana" color="mediumblue">
	<%
		String account = request.getSession().getAttribute("firstName").toString()+ " "+  request.getSession().getAttribute("lastName").toString();
		out.println("Hello, " + account);
	%>
</font>
</h2>

<h2 class="glow">Josiah's Online Store </h2>



 <div class="topnav">
  <a class ="logout" href ="login.jsp">Log Out</a>
  <a class="active" href="home.jsp">Home</a>
  
  <a href="#about">Favorite Sellers</a>
  <a href="#contact">Favorite Items</a>
 
  <div class="search-container">
  
    <form method="Post" action="OnlineStoreControlServlet">
			<input type="hidden" name="action" value="searchItemsByCategory" />
		
					<input type="text" name="category" placeholder="category..." required>
				<button type="submit">Search</button>
    </form>
  </div>
</div> 




	</body>
	</html>