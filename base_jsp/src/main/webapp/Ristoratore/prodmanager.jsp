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
  <a  href="HomeRistoratore.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<ProdottoDTO> listProdotto = (List<ProdottoDTO>) request.getAttribute("listProdotto");
		List<RistoranteDTO> listRisto = (List<RistoranteDTO>) request.getAttribute("listRisto");
	%>

<br>

	<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
			<th>Prezzo</th>
			<th>Ristorante</th>
			<th></th>
			<th></th>
		</tr>
		<%
			String nomeRistorante = "";
			for (ProdottoDTO prod : listProdotto) {
				for (RistoranteDTO risto : listRisto){
					if (prod.getIdrist() == risto.getId()) {
						nomeRistorante = risto.getNome().toString();
						break;
					}
				}
		%>
		<tr>
			<td><%=prod.getDescrizione()%></td>
			<td><%=prod.getQuantita()%></td>
			<td><%=prod.getPrezzo()%></td>
			<td><%=nomeRistorante%></td>
			<td><a href=RistoProdottiServlet?mode=read&update=true&id=<%=prod.getIdprodotto()%>>Edit</a>
			</td>
			<td><a href=RistoProdottiServlet?mode=delete&id=<%=prod.getIdprodotto()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="RistoProdottiServlet?mode=INSERTPROD" method="post">
  <div class="row">
    <div class="col-25">
      <label for="nome" id="nome">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" placeholder="inserisci nome">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="paese">Quantità</label>
    </div>
    <div class="col-75">
      <input type="text" id="quantita" name="quantita" placeholder="inserisci quantita"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="piva">Prezzo</label>
    </div>
    <div class="col-75">
      <input type="text" id="prezzo" name="prezzo" placeholder="inserisci prezzo"> 
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="type">Ristorante</label>
    </div>
   		 <div class="col-75">
 			<select id="idrist" name="idrist">
 				<%
					for (RistoranteDTO r : listRisto) {
				%>
  				<option value=<%= r.getId()%>><%= r.getNome()%></option>
  				<%}%>
			</select>
    	</div>
  </div>
     <button type="submit" >Insert</button>
      <%
      if (!request.getAttribute("messaggio").toString().equals("")){
      String messaggio = request.getAttribute("messaggio").toString();%>
      <p><%= messaggio %></p>
      <%}%>
</form>
<br>

</div>
</body>
</html>