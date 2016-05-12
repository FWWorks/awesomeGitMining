<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Detail</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/indexpage.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/animate.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/css/simplify.min.css"/>" rel="stylesheet" type="text/css" media="all">
</head>
<body class="light-gray-bg">
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav">
            <ul>
                <li><a href="/index.jsp">Home</a></li>
                <li><a href="/repo/repos?pager.offset=0">Repository</a></li>
                <li><a href="/user/users?pager.offset=0">User</a></li>
                <li><a href="#">Repository Statistics</a></li>
                <li><a href="#">User Statistics</a></li>
                <li><a href="#" class="active">Recommended</a> </li>
            </ul>
        </nav>

        <div class="dropdown navbar-right">
            <%session.setAttribute("backuri","/");%>
            <%
                if(session.getAttribute("loginMember")==null){
            %>
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
                Visitors <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <li>
                    <a href="/login.jsp">Sign in</a>
                </li>
                <li>
                    <a href="/register">Sign up</a>
                </li>
            </ul>
            <%
            }else{
            %>
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
                <%=session.getAttribute("loginMember")%><b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
                <li>
                    <a href="#">Favorite Repositories</a>
                </li>
                <li>
                    <a href="/logout">Log out</a>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </div>
</div>

<div class="templatemo-flex-row"> 
    <div class="templatemo-content col-1 light-gray-bg"> 
        <div class="templatemo-content-widget white-bg col-1 animated fadeInUp"> 
            <h3 class="m-top-md m-bottom-sm">Recommendation from your search records</h3> 
            <div class="panel-body panel-default margin-10"> 
                <ul class="dashboard-list"> 
                    <c:forEach items="${search}" var="repo">
                        <li>
                            <h3><strong><a href="${repo.fullName }">${repo.fullName }</a></strong></h3>
                            <p style="text-align: right"><strong>${repo.language}</strong></p>
                            <p><span><strong>Subscribers:</strong>&nbsp;${repo.subscribersCount}</span>
                                <span><strong>Forks:</strong>&nbsp;${repo.forksCount}</span>
                                <span><strong>Stargazers:</strong>&nbsp;${repo.stargazersCount}</span>
                            </p>
                            <p class="blue-text">${repo.description}</p>
                            <strong>Last Updated:</strong>&nbsp;${repo.updatedAt}<br>
                        </li>
                    </c:forEach>
                </ul> 
            </div>  
        </div> 

        <div class="templatemo-content-widget white-bg col-1 animated fadeInUp"> 
            <h3 class="m-top-md m-bottom-sm">Recommendation from your favorite records</h3> 
            <div class="panel-body panel-default margin-10"> 
                <ul class="dashboard-list"> 
                    <c:forEach items="${star}" var="repo">
                        <li>
                            <h3><strong><a href="${repo.fullName }">${repo.fullName }</a></strong></h3>
                            <p style="text-align: right"><strong>${repo.language}</strong></p>
                            <p><span><strong>Subscribers:</strong>&nbsp;${repo.subscribersCount}</span>
                                <span><strong>Forks:</strong>&nbsp;${repo.forksCount}</span>
                                <span><strong>Stargazers:</strong>&nbsp;${repo.stargazersCount}</span>
                            </p>
                            <p class="blue-text">${repo.description}</p>
                            <strong>Last Updated:</strong>&nbsp;${repo.updatedAt}<br>
                        </li>
                    </c:forEach>
                </ul> 
            </div>  
        </div> 
    </div>
</div> 

<footer class="text-right">
    <p><strong>Copyright &copy; 2A617.</strong> All Rights Reserved</p>
</footer>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
