<%--
  Created by IntelliJ IDEA.
  User: phaniginjupalli
  Date: 11/28/22
  Time: 1:05 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Company</title>
    <link href="<c:url value="/static-resources/css/add-company.css" />" rel="stylesheet">
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
    <div class="add-company-block">
        <h1>Onboard a new company</h1>
        <%--@elvariable id="company" type="com.example.jobportal.pojo.Company"--%>
        <form:form action="/jobs/admin/onboard/" method="post" modelAttribute="company" enctype="multipart/form-data">
            <div>
                <form:label for="name" path="name">Company Name</form:label>
                <form:input path="name" name="name" id="name" required="true"/> &nbsp; <form:errors cssClass="formErrors" path="name"/>
            </div>
            <div>
                <form:label for="logo" path="logo">Logo: </form:label>
                <form:input type="file" id="logo" name="logo" path="logo" required="true"/> &nbsp; <form:errors cssClass="formErrors" path="logo"/>
            </div>
            <div>
                <input type="submit" value="Onboard" />
            </div>
        </form:form>
    </div>
</div>
</body>
</html>

