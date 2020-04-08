<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>The User's Info</title>
</head>
<body>

<h4>User's Info</h4>

<form:form action="/user" modelAttribute="user" method="GET">

    <div>Name: ${user.name}</div>
    <div>Login: ${user.login}</div>
    <div>Password: ${user.password}</div>
    <div>Role: ${user.roles}</div>

</form:form>

<br>

<a href="<c:url value="/logout" />">Logout</a>

</body>
</html>
