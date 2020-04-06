<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>查询所有账户，哈哈哈哈啊打发打发</h2>
<c:forEach items="${list}" var="${account}">
    <tr>
        <td>${account.toString()}</td>     
    </tr>
</c:forEach>
</body>
</html>
