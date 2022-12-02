<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Signup recruiter</title>
    <link href="<c:url value="/static-resources/css/login.css" />" rel="stylesheet">
</head>
<body>
<div class="main-block">
    <div class="signup-block">
        <h1>Signup to find new talent</h1>
        <%--@elvariable id="user" type="com.example.jobportal.pojo.User"--%>
        <form:form action="/jobs/recruiter/signup" method="post" modelAttribute="user">
                <div>
                    <label for="company">Company:</label>
                    <select name="company" id="company">
                    <c:forEach var="company" items="${companies}">
                        <option value=${company}>${company.name}</option>
                    </c:forEach>
                    </select>
                </div>
            <div class="nameblock">
            <form:label for="firstname" path="firstName">FirstName</form:label>
            <form:input path="firstName" name="firstname" id="firstname" /> <form:errors path="firstName"/>
            <form:label for="lastname" path="lastName">LastName</form:label>
            <form:input path="lastName" name="lastname" id="lastname" /><form:errors path="lastName"/>
            </div>
            <div>
            <form:label for="email" path="email">Email</form:label>
            <form:input path="email" name="email" id="email" /><form:errors path="email"/>
            </div>
            <div>
                <form:label for="password" path="password">Password:</form:label>
                <form:input
                        type="password"
                        id="password"
                        name="password"
                        path="password"
                /><form:errors path="password"/>
            </div>
            <div>
                <form:label for="confirmPassword" path="confirmPassword">Confirm Password:</form:label>
                <form:input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        path="confirmPassword"
                /><form:errors path="confirmPassword"/>
            </div>
            <input type="submit" value="Sign up" />
        </form:form>
    </div>
</div>
</body>
</html>
