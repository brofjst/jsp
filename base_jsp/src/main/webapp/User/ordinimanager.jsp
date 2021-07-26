<%@ page import="it.contrader.service.OrdineService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OrdineDTO"
	import="it.contrader.dto.RistoranteDTO"
	import="it.contrader.dto.ProdottoDTO"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Ord Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="HomeUser.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<OrdineDTO> listOrdini = (List<OrdineDTO>) request.getAttribute("listOrdini");
		List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("listUtenti");
		List<RistoranteDTO> listRisto = (List<RistoranteDTO>) request.getAttribute("listRisto");
		List<ProdottoDTO> listProdotto = (List<ProdottoDTO>) request.getAttribute("listProdotto");
	%>

<br>

	<table>
		<tr>
			<th>Cliente</th>
			<th>Ristorante</th>
			<th>Prodotto</th>
			<th>Quantità</th>
			<th>Data</th>
			<th>Prezzo</th>
			<th>Flag</th>
			<th></th>
		</tr>
		<%
			for (OrdineDTO ord : listOrdini) {
				String username = new String();
				String ristoname = new String();
				String prodname = new String();
				try{
				for (UserDTO u : listUser) {
					if (ord.getIduser() == u.getId()){
						username = u.getUsername().toString();
						break;
					}
				
				for (RistoranteDTO risto : listRisto){
					if (risto.getId() == ord.getIdrist()){
						ristoname = risto.getNome().toString();
						break;
					}
				}
				
				for (ProdottoDTO prod : listProdotto){
					if (prod.getIdprodotto() == ord.getIdprodotto()){
						prodname = prod.getDescrizione();
						break;
					}
				}
			}
				} catch (Exception e){
					System.out.println(e.getMessage());
				}
				
		%>
		<tr>
			<td><%=username%></td>
			<td><%=ristoname%></td>
			<td><%=prodname%></td>
			<td><%=ord.getQuantita()%></td>
			<td><%=ord.getData()%></td>
			<td><%=ord.getPrezzo()%></td>
			<td><%=ord.getFlag()%></td>
			<td><a href=UserOrdiniServlet?mode=delete&idordine=<%=ord.getIdordine()%>>Delete</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	
	  <%
      if (!request.getAttribute("messaggio").toString().equals("")){ %>
    	<form id="floatright" action="RistoProdottiServlet?mode=INSERTPROD" method="post">
      	<% String messaggio = request.getAttribute("messaggio").toString();%>
      	<p><%= messaggio %></p>
      	</form>
      <%}%>
	
	

<br>

</div>
</body>
</html>