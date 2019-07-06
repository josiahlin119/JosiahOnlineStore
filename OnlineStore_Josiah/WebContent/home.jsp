<%@ page import="java.util.*,OnlineStore_Josiah.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="staticTheme.jsp" />

<!DOCTYPE html>
<html>


<head>

<meta charset="UTF-8">

</head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

* {box-sizing: border-box;}

.formWrapper input[type=text], textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: darkturquoise;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: mediumspringgreen;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width: 20%;
  position: absolute;
  top: 30%;
  left: 40%;
  
}
</style>

<body>


		

<div class="container">
<div class ="formWrapper"> 
 <form method="Post" action="OnlineStoreControlServlet">
    <label for="title">Title</label>
    <input type="text" id="title" name="title" placeholder="Title Of Item...">

    <label for="Price">Price</label>
    <input type="text" id="price" name="price" placeholder="Set a price...">

    <label for="category">Categories</label>
    <input type="text" id="category" name="category" placeholder="...#...#...">
    
    <label for="description">Subject</label>
    <textarea id="description" name="description" placeholder="Write a brief description of this item..." style="height:100px"></textarea>

    <input type="submit" value="Post Item">
  </form>
  
  
  
  </div>
</div>
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


