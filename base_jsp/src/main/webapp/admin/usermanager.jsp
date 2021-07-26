<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <!-- <a class="active" href="UserServlet?mode=userlist">Users</a> -->
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<UserDTO> list = (List<UserDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Usertype</th>
			<th>Cognome</th>
			<th>Mail</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (UserDTO u : list) {
		%>
		<tr>
			<td><%=u.getUsername()%></td>
			<td><%=u.getPassword()%></td>
			<td><%=u.getUsertype()%></td>
			<td><%=u.getCognome()%></td>
			<td><%=u.getMail()%></td>
			<td><a href=AdminUserServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=AdminUserServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="AdminUserServlet?mode=INSERTUSER" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" placeholder="inserisci username">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input type="text" id="pass" name="password" placeholder="inserisci password"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Cognome</label>
    </div>
    <div class="col-75">
      <input type="text" id="cogn" name="cognome" placeholder="inserisci cognome"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Mail</label>
    </div>
    <div class="col-75">
      <input type="text" id="mail" name="mail" placeholder="inserisci mail"> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype">
  				<option value="ADMIN">ADMIN</option>
  				<option value="USER">USER</option>
  				<option value="RISTORATORE">RISTORATORE</option>
 
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