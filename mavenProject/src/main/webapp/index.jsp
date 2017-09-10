<%--
  Created by IntelliJ IDEA.
  User: jwliu
  Date: 2017/9/9
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="myController" method="post" >
    <input name="name">
    <input type="submit" value="Submit">
    <label>Name is : ${name}</label>
</form>

</body>
</html>
