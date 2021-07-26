<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.RistoranteDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Risto</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="/Ristoratore/HomeRistoratore.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%RistoranteDTO u = (RistoranteDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Nome</th>
		<th>Paese</th>
		<th>Partita Iva</th>
		<th>Indirizzo</th>
	</tr>
	<tr>
		<td> <%=u.getNome()%> </td>
		<td> <%=u.getPaese()%> </td>
		<td> <%=u.getPiva()%> </td>
		<td> <%=u.getIndirizzo()%> </td>
	</tr>	
</table>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

</body>
</html>