<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ProdottoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Risto</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="HomeRistoratore.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%ProdottoDTO u = (ProdottoDTO) request.getAttribute("dto");%>


<form id="floatleft" action="RistoProdottiServlet?mode=update&id=<%=u.getIdprodotto() %>&idrist=<%=u.getIdrist() %>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="nome">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" value=<%=u.getDescrizione()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="paese">Quantita</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="quantita" name="quantita" value=<%=u.getQuantita()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="piva">Prezzo</label>
    </div>
    <div class="col-75">
      <input type="text" id="prezzo" name="prezzo" value=<%=u.getPrezzo()%>>
    </div>
  </div>
      <button type="submit">Edit</button>
</form>

	
</div>
<br>
</body>
</html>