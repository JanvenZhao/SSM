<%--
  Created by IntelliJ IDEA.
  User: janven
  Date: 2017/11/28
  Time: 下午4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wanggenshen_sx
  Date: 2016/12/26
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Show Page22</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>LastName</th>
        <th>Email</th>
    </tr>

    <c:forEach items="${requestScope.employees}" var="emp">
        <tr>
            <th>${emp.id}</th>
            <th>${emp.lastName}</th>
            <th>${emp.email}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
