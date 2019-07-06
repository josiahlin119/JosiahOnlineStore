<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="staticTheme.jsp" />
<!DOCTYPE html>
<html>

<head>
<link type="text/css" rel="stylesheet" href="resources/css/ZebraStripedTable.css">

</head>
<meta charset="UTF-8">



<body>


	<table>

		<tr>
			<th>Title</th>
			<th>SellerId</th>
			<th>Descriptions</th>
			<th>Category</th>
			<th>Review Item</th>
			<th>Seller Info</th>

		</tr>

		<c:forEach var="theItem" items="${items}">

			<!-- Set up a link for each item so that
				other users can review each item and give rating -->
			<tr>
				<td>${theItem.title}</td>
				<td>${theItem.sellerId}</td>
				<td>${theItem.descriptions }</td>
				<td>${theItem.category}</td>


	<c:url var="reviewItemLink" value="OnlineStoreControlServlet">
				<c:param name="action" value="getItem"/>
				<c:param name="itemId" value="${theItem.itemId}" />
			</c:url>

			<c:url var="sellerProfileLink" value="UserControlServlet">
				<c:param name="action" value="sellerInfo" />
				<c:param name="sellerId" value="${theItem.sellerId}" />
			</c:url>
			
				<td><a href="${reviewItemLink}">Review Item</a></td>
				
				<td><a href="${sellerInfoLink}"> Seller Info</a></td>



			</tr>

		</c:forEach>

	</table>






	</div>

	</div>





</body>


</html>