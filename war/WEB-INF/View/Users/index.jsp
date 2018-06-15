<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Fernando
  Date: 07/06/2018
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User usuario = (User) request.getAttribute("User"); %>
<% List<User> userList = (List<User>) request.getAttribute("UsersList");%>
<html>
<head>
    <title>Titulo</title>

    <meta name="google-signin-client_id" content="746890482047-c734fgap3p3vb6bdoquufn60bsh2p8l9.apps.googleusercontent.com">

    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <style>
        .postLink{
            color: blue;
            font-size: large;
            cursor: pointer;

            transition: color 250ms ease-in;
        }
        .postLink:hover{
            color: green;
            font-size: larger;
        }
    </style>

</head>
<body>

User: <%= usuario.getName()%><img src="<%=usuario.getImgUrl()%>"><br/>
You can

<a href="#" onclick="signOut();">Sign out</a> there v:<br />
<br />
<br />
<br />

<table>
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Img</td>
        <td>Actions</td>
    </tr>

<% for (int i = 0; i < userList.size(); i++) {%>
    <% User user = userList.get(i); %>
    <tr>
        <td><%= user.getName()%></td>
        <td><%= user.getEmail()%></td>
        <td><img src="<%= user.getImgUrl()%>"/></td>
        <td>
            <a class="postLink" onclick="postRedirect('users/view',{action:'viewRedirect',userID:'<%=user.getId()%>'})">View</a>
            | <a class="postLink" onclick="postRedirect('users/view',{action:'editRedirect',userID:'<%=user.getId()%>'})">Edit</a>
            | <a class="postLink" onclick="postRedirect('users/add',{action:'delete',userID:'<%=user.getId()%>'})">Delete</a></td>
    </tr>
<% } %>

</table>

<script>
    function signOut() {

        var req = new XMLHttpRequest();

        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200){
                <%-- Hace una solicitud al metodo UsersControllerView, y recibe una url. Luego, cierra la sesion de Google, y luego redirige
                 a la url recibida.--%>
                document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=" + req.responseText;
            }
        };

        req.open("GET","/users/view?action=closeSession",true);
        req.send();

    }

    function postRedirect(url, postData){

        var postForm = document.createElement("form");
        postForm.action = url;
        postForm.method = "POST";

        postForm.style.display = "none";

        for (var key in postData){
            if (postData.hasOwnProperty(key)){
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = key;
                input.value = postData[key];
                postForm.appendChild(input);
            }
        }

        document.body.appendChild(postForm);

        postForm.submit();

    }
</script>
</body>
</html>
