
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Company list</title>
    <link href="<c:url value="/static-resources/css/company-list.css" />" rel="stylesheet">
</head>
<body>
<div class="main-block">
    <div class="navigation-header">
        <div>
            <a href="http://localhost:8080/jobs/admin/onboard">Add Company</a>
        </div>
        <div>
            <a href="http://localhost:8080/jobs/admin/company-list">Companies</a>
        </div>
        <div>
            <a href="http://localhost:8080/jobs/logout">Logout</a>
        </div>
    </div>
    <h1>Added companies list</h1>
    <div class="company-list-block">
        <%--@elvariable id="company" type="com.example.jobportal.pojo.Company"--%>
        <c:forEach var="company" items="${companies}">
            <div class="company-card">
                <div class="company-card-logo">
                    <img alt="img" src="data:image/jpeg;base64, ${company.base64logoFile}"/>
                </div>
                <div class="company-card-name">
                        ${company.name}
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

