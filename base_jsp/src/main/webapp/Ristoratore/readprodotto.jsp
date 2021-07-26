<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ProdottoDTO"
    import="it.contrader.dto.RistoranteDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Prodotto</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="/Ristoratore/HomeRistoratore.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
	<%//ProdottoDTO u = (ProdottoDTO) request.getAttribute("dto");
		List<ProdottoDTO> listProdotto = (List<ProdottoDTO>) request.getAttribute("listProdotto");
		List<RistoranteDTO> listRisto = (List<RistoranteDTO>) request.getAttribute("listRisto");
	%>


<table>
	<tr> 
		<th>Nome</th>
		<th>Quantità</th>
		<th>Prezzo</th>
		<th>Ristorante</th>
	</tr>
	<tr>
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
		<td> <%=prod.getDescrizione()%> </td>
		<td> <%=prod.getQuantita()%> </td>
		<td> <%=prod.getPrezzo()%> </td>
		<td> <%=nomeRistorante%> </td>
	</tr>	
		<%
			}
		%>
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