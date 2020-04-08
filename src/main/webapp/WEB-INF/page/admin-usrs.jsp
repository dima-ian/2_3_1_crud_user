<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>

    <title>CRUD-ADMIN</title>

</head>
<body>

<h2>Users</h2>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.password}</td>
        <td>
            <c:forEach var="role" items="${user.roles}">
                ${role.role}
            </c:forEach>
        </td>
        <td>
            <form:form method="POST" action="/admin/delete">
                <input type="number" hidden name="id" value="${user.id}"/>
                <input type="submit" name="delete" value="Delete"/>
            </form:form>
        </td>
        <td>
            <a href="/admin/edit?id=${user.id}">Edit</a>
        </td>

        </tr>
    </c:forEach>

    <form:form action="/admin/add" method="POST" modelAttribute="user">
        <tr>
            <td>Add new user</td>
            <td>
                Name <form:input path="name"></form:input>
            </td>
            <td>
                Login <form:input path="login"></form:input>
            </td>
            <td>
                Password <form:input path="password"></form:input>
            </td>
            <td>Role <select name="role">
                <option value="1">ROLE_ADMIN</option>
                <option value="2">ROLE_USER</option>
            </select>
            </td>
            <td>
                <button type="submit">Submit</button>
            </td>
        </tr>
    </form:form>


</table>

<a href="<c:url value="/logout" />">Logout</a>

</body>
</html>