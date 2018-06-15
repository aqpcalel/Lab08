<%@ page import="model.User" %>
<%@ page import="model.Role" %>
<%--
  Created by IntelliJ IDEA.
  User: Fernando
  Date: 07/06/2018
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%  User user = (User) request.getAttribute("User");
    boolean editAllowed = (Boolean) request.getAttribute("editAllowed");
    String action = (String) request.getAttribute("action");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=action%> a User</title>
</head>
<body>

Datos del usuario:<br />
<br />

<% if (editAllowed) {%>

    <form action="./add" method="post">

        <input name="userID" value="<%=user.getId()%>" type="hidden">
        <input name="action" value="update" type="hidden">

        Nombre:<br />
        <input name="userName" value="<%=user.getName()%>" placeholder="Nombre" required><br/>
        <br/>
        Email:<br />
        <input name="userEmail" value="<%=user.getEmail()%>" placeholder="Email" type="email" required><br />
        <br />
        Imagen de perfil<br />
        <input name="userImg" value="<%=user.getImgUrl()%>" placeholder="Link de la imagen" required><br />
        <br />
        Rol<br />
        <input name="userRole" value="<%=user.getRole()%>" placeholder="Rol" required><br />
        <br />
        <input type="submit"><br />

    </form>

<% } else {%>

    <img src="<%=user.getImgUrl()%>"><br />
    <br />

    Nombre: <%=user.getName()%><br />
    Email: <%=user.getEmail()%><br />
    Rol: <%= user.getRole()%><br />

<% } %>

Eso es todo :c

</body>
</html>
