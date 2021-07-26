<%@page import="it.contrader.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.RistoranteDTO"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Risto Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="HomeUser.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<RistoranteDTO> list = (List<RistoranteDTO>) request.getAttribute("ristorantiList");
	%>

<br>

	<table>
		<tr>
			<th>Nome</th>
			<th>Paese</th>
			<th>P.iva</th>
			<th>indirizzo</th>
			<th></th>
		</tr>
		<%
			for (RistoranteDTO u : list) {
		%>
		<tr>
			<td><%=u.getNome()%></td>
			<td><%=u.getPaese()%></td>
			<td><%=u.getPiva()%></td>
			<td><%=u.getIndirizzo()%></td>
			<td><a href=UserRistoServlet?mode=prodlist&idristorante=<%=u.getId()%>>Seleziona</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
<br>

</div>
</body>
</html>