<%@ page import="java.util.*,OnlineStore_Josiah.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="staticTheme.jsp" />

<!DOCTYPE html>
<html>


<head>

<meta charset="UTF-8">

</head>
<body>



	<div id="cover">
		<form method="Post" action="OnlineStoreControlServlet">
			<input type="hidden" name="action" value="searchItemWithCategory" />
			<div class="tb">
				<div class="td">
					<input type="text" name="Category" placeholder="Category" required>
				</div>
				<div class="td" id="s-cover">
					<button type="submit" value="search">
						<div id="s-circle"></div>
						<span></span>
					</button>
				</div>
			</div>
		</form>
	</div>


	<br />
	<br />

	<!-- The id of the session user is hidden in request so i dont need to explicity add it here -->

	<br />
	<br />
	<br />

	<c:if test="${theJoke != null}">
		<font size="6" color="red">You have successfully posted your
			item</font>
	</c:if>
	
	<c:if test="${warning !=null }">
	
	<font size="6" color="red"> 
			Warning! You cannot post more than five Items per day</font>
	</c:if> 


	
	



</body>



</html>


