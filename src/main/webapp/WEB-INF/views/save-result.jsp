<%--
  Created by IntelliJ IDEA.
  User: 이정헌
  Date: 2025-08-06
  Time: 오후 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공!!!
<ul>
    <!-- $와 {}를 이용해서 request의 attribute에 담긴 데이터를 편리하게 조회할 수 있다. -->
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
