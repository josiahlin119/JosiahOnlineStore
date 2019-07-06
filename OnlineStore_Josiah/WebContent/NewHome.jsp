<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
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
  top: 20%;
  left: 40%;
  
}
</style>

<title>Josiah's Online Store</title>
</head>
<body>



<div class="container">
  <form action="/action_page.php">
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

</body>
</html>
