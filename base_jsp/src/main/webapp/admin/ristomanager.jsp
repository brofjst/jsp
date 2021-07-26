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
  <a  href="homeadmin.jsp">Home</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<UserDTO> ristoratoriList = (List<UserDTO>) request.getAttribute("ristoratoriList");
		List<RistoranteDTO> list = (List<RistoranteDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Nome</th>
			<th>Paese</th>
			<th>P.iva</th>
			<th>indirizzo</th>
			<th>Proprietario</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (RistoranteDTO u : list) {
				String prop = "";
				for (UserDTO r : ristoratoriList) {
					if (u.getIduser() == r.getId()){
						prop = r.getUsername().toString();
					}
				}
				
		%>
		<tr>
			<td><%=u.getNome()%></td>
			<td><%=u.getPaese()%></td>
			<td><%=u.getPiva()%></td>
			<td><%=u.getIndirizzo()%></td>
			<td><%=prop%></td>
			<td><a href=AdminRistorantiServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=AdminRistorantiServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="AdminRistorantiServlet?mode=INSERTRIST" method="post">
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
     <label for="paese">Paese</label>
    </div>
    <div class="col-75">
      <input type="text" id="paese" name="paese" placeholder="inserisci paese"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="piva">Partita iva</label>
    </div>
    <div class="col-75">
      <input type="text" id="piva" name="piva" placeholder="inserisci partita iva"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="indirizzo">Indirizzo</label>
    </div>
    <div class="col-75">
      <input type="text" id="indirizzo" name="indirizzo" placeholder="inserisci indirizzo"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">Proprietario</label>
    </div>
   		 <div class="col-75">
 			<select id="idrist" name="idrist">
 				<%
					for (UserDTO r : ristoratoriList) {
				%>
  				<option value=<%= r.getId()%>><%= r.getUsername()%></option>
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