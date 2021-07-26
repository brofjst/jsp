<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Ristoratore</title>
<!--  --><link href="../css/vittoriostyle.css" rel="stylesheet">

</head>
<body>
<%@include file="../css/header.jsp"%>


<div class="navbar">
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
<h1>Welcome <%=request.getSession().getAttribute("userName")%></h1>
<p>Cosa vuoi fare?</p>
</div>


<div  id="parentmio">
 <div >
	<form class="FiltraCategorie" action="UserRistoServlet?mode=RISTLIST"  method="post"> 
 		<button type="submit" id="userBut" value="prodlist" name="mode">Ristoranti</button> 
 	</form>
 </div>
 <div >
 	<form class="FiltraCategorie" action="UserCarrelloServlet?mode=CARRLIST"  method="post"> 
		<button type="submit" id="ristBut" value="carrello" name="mode">Carrello</button>
	</form>
	</div>
	<div >
 	<form class="FiltraCategorie" action="UserOrdiniServlet?mode=ORDLIST"  method="post"> 
		<button type="submit" id="ordBut" value="ordlist" name="mode">Ordini</button>
    </form>
  </div>
</div>

</body>
</html>