<%@page import="it.contrader.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.RistoranteDTO"
	import="it.contrader.dto.ProdottoDTO"
	import="it.contrader.dto.UserDTO"
	import="java.util.HashMap"
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Carrello Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a href="HomeUser.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		HashMap<Integer,Integer> carrelloQuantita = (HashMap<Integer,Integer>) request.getSession().getAttribute("carrelloQuantita");
		HashMap<Integer,Float> carrelloPrezzo = (HashMap<Integer,Float>) request.getSession().getAttribute("carrelloPrezzo");
		List<ProdottoDTO> listProdotto = (List<ProdottoDTO>) request.getAttribute("prodottiList");
		List<RistoranteDTO> listRisto = (List<RistoranteDTO>) request.getAttribute("ristorantiList");
	%>

<br>

	<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
			<th>Prezzo cadauno</th>
			<th>Prezzo totale</th>
			<th>Ristorante</th>
			<th></th>
		</tr>
		<%
		for (int keys : carrelloQuantita.keySet()) {
			String nomeProdotto = "";
			String nomeRistorante = "";
			int idrist = 0;
			
			for(ProdottoDTO p : listProdotto){
				if(p.getIdprodotto() == keys){
					nomeProdotto = p.getDescrizione();
					idrist = p.getIdrist();
					break;
				}
			}
			
			for(RistoranteDTO r : listRisto){
				if(r.getId() == idrist){
					nomeRistorante = r.getNome();
					break;
				}
			}
		%>
		<tr>
			<td><%=nomeProdotto%></td>
			<td><%=carrelloQuantita.get(keys)%></td>
			<td><%=carrelloPrezzo.get(keys)%></td>
			<td><%=carrelloQuantita.get(keys)*carrelloPrezzo.get(keys)%></td>
			<td><%=nomeRistorante%></td>
			<td><a href=UserCarrelloServlet?mode=delete&idprodotto=<%=keys%>>Delete</a>
		</tr>
		<%
			}
		%>
	</table>
	


	<form id="floatright" action="UserCarrelloServlet" method="post">
		<input type="submit" name="mode" value="Svuota carrello">
		<input type="submit" name="mode" value="Ordina">
    <%if (!request.getAttribute("messaggio").toString().equals("")){%>
      <% String messaggio = request.getAttribute("messaggio").toString();%>
      <p><%=messaggio %></p>
    <%}%>
	</form>

<br>

</div>
</body>
</html>