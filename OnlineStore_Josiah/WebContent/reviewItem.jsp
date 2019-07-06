<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="staticTheme.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<link type="text/css" rel="stylesheet" href="resources/css/ZebraStripedTable.css">
</head>
<body>
	<table>

		<tr>
			<th>Title</th>
			<th>SellerId</th>
			<th>Descriptions</th>
			<th>Category</th>
			<th>Seller Info</th>
		</tr>

		<!-- Set up a link for each item so that
				other users can review each item and give rating -->
		<tr>
			<td>${theItem.title}</td>
			<td>${theItem.sellerId}</td>
			<td>${theItem.descriptions }</td>
			<td>${theItem.category}</td>


			<c:url var="sellerProfileLink" value="UserControlServlet">
				<c:param name="action" value="verifyFriend" />
				<c:param name="sellerId" value="${theItem.sellerId}" />
			</c:url>
			<td><a href="${sellerInfoLink}"> Seller Info</a></td>

		</tr>

	</table>
	<br/><br/>
	<div>
	Give a Score <select name="rating">
			<option>Excellent</option>
			<option>Good</option>
			<option>Fair</option>
			<option>Poor</option>
	

		</select> 
		</div>

</body>
</html>