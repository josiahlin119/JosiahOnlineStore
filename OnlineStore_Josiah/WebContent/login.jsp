<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*,OnlineStore_Josiah.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  background-image: url("/OnlineStore_Josiah/resources/shopping.jpg");
 
   height: 1200px;
  background-position: center;
  background-repeat: repeat;
  background-size: 900px 1100px;
  position: relative;
  
  

font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid yellow;
  box-sizing: border-box;
 background: #f1f1f1;
}


/* Set a style for all buttons */
button {
  background-color: #FFD700;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 30%; /* Full width */
  height: 30%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 20%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
  h2{
  postion:center;
  }
}
</style>
</head>
<body>






	<c:if test="${successfullyRegistered != null}">
		<font size="6" color="skyblue">Congratulation, You are our new member now</font>
	</c:if>
<br/>



  
  <form class="modal-content animate" action="OnlineStoreControlServlet" method="post">
  
							<input type="hidden" name="action" value="login" required/>
							
							<h2><font color = "deeppink"> Josiah's Online Store</font></h2>
    <div class="imgcontainer">
      
      
      
      <img src="/OnlineStore_Josiah/resources/onlineStore.png"  alt="Online Store" style="width:30%;height:25%;"class="avatar">
    </div>

    <div class="container">
      <label for="userId"><b>&nbsp User Id</b></label>
      <input type="text" placeholder="User Id" name="userId" required>

      <label for="password"><b> &nbsp Password</b></label>
      <input type="password" placeholder="Password" name="password" required>
        
      <button type="submit">Login</button>
     
    </div>
    
    
       <div class="container" style="background-color:#f1f1f1">
     
     
      <div class="container signin">
   <p>You don't have an account? <a href="signup.jsp">Sign up</a>.</p>
     
  </div>
    </div>
   
     </form>

 
 




</body>
</html>