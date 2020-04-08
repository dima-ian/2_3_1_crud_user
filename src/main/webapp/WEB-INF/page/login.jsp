<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<h4>Login Form</h4>

<form action="<c:url value='/login' />" method="post">

    <table>
        <tr>
            <td>Username:</td>
            <td><input type='text' name='name' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>

</form>

</body>
</html>
