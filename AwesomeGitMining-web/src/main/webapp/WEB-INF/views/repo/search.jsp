<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>search result</title>
</head>
<body>
    搜索结果
    <br>
    <c:forEach items="${list }" var="repo">
        <a href="${repo.fullName}">${repo.fullName}</a>----<a href="/user/${repo.ownerName}">relatedUser</a><br/>
    </c:forEach>
</body>
</html>
