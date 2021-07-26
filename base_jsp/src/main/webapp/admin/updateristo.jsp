<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.RistoranteDTO"%>
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
  <a href="homeadmin.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%RistoranteDTO u = (RistoranteDTO) request.getAttribute("dto");%>


<form id="floatleft" action="AdminRistorantiServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="nome">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" value=<%=u.getNome()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="paese">Paese</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="paese" name="paese" value=<%=u.getPaese()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="piva">Partita iva</label>
    </div>
    <div class="col-75">
      <input type="text" id="piva" name="piva" value=<%=u.getPiva()%>>
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="indirizzo">Indirizzo</label>
    </div>
    <div class="col-75">
      <input type="text" id="indirizzo" name="indirizzo" value=<%=u.getIndirizzo()%>>
    </div>
  </div>
      <button type="submit">Edit</button>
</form>

	
</div>
<br>
</body>
</html>