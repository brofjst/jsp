<%@page import="it.contrader.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.RistoranteDTO"
	import="it.contrader.dto.ProdottoDTO"
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
  <a href="HomeUser.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<ProdottoDTO> listProdotto = (List<ProdottoDTO>) request.getAttribute("prodottiList");
		List<RistoranteDTO> listRisto = (List<RistoranteDTO>) request.getAttribute("ristorantiList");
	%>

<br>

	<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
			<th>Prezzo</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (ProdottoDTO prod : listProdotto) {
				if(prod.getQuantita()>0){
		%>
		<tr>
			<td><%=prod.getDescrizione()%></td>
			<td><%=prod.getQuantita()%></td>
			<td><%=prod.getPrezzo()%></td>
			
			<form class="inputuser" action="UserRistoServlet?idprodotto=<%=prod.getIdprodotto()%>&prezzo=<%=prod.getPrezzo()%>" method="post">
			<td><input id ="inputQuantita" name="inputQuantita" size="1" type="number" step="1" min = "1" max = "<%=prod.getQuantita()%>" >quanti vuoi prenderne?</td>
			<td><input type="hidden" name="mode" value="ADDCARRELLO"></td>
			<td><button type="submit" value=<%=prod.getIdprodotto()%> name="idprodotto">Prendi</button></td>
			</form>
		</tr>
		<%
			}}
		%>
	</table>
	
	<%
    if (!request.getAttribute("messaggio").toString().equals("")){%>
	<form id="floatright" action="UserRistoServlet?mode=INSERTRIST" method="post">
      <% String messaggio = request.getAttribute("messaggio").toString();%>
      <p><%=messaggio %></p>
	</form>
	<%}%>
<br>

</div>
</body>
</html>