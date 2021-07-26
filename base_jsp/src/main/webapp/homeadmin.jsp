<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@include file="css/header.jsp"%>


<div class="navbar">
  <a class="active" href="homeadmin.jsp">Home</a>
  <!-- <a href="UserServlet?mode=userlist">Users</a>-->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
<h1>Welcome <%=request.getSession().getAttribute("userName")%></h1>
<p>Cosa vuoi gestire?</p>
</div>

<!--<div id="inputpanel" >  -->

<div  id="parentmio">
 <div >
	<form class="FiltraCategorie" action="AdminUserServlet"  method="post"> 
 		<button type="submit" id="userBut" value="userlist" name="mode">Utenti</button> 
 	</form>
 </div>
 <div >
 	<form class="FiltraCategorie" action="AdminRistorantiServlet"  method="post"> 
		<button type="submit" id="ristBut" value="ristlist" name="mode">Ristoranti</button>
	</form>
	</div>
	<div >
 	<form class="FiltraCategorie" action="AdminOrdiniServlet"  method="post"> 
		<button type="submit" id="ordBut" value="ordlist" name="mode">Ordini</button>
    </form>
  </div>
</div>

</body>
</html>