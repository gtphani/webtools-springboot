<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link href="<c:url value="/static-resources/css/login.css" />" rel="stylesheet">
  </head>
  <body>
    <div class="main-block">
      <div class="signup-block">
        <div class="signup-links">
          <a href="http://localhost:8080/jobs/recruiter/signup">Signup as recruiter</a>
        </div>
        <div class="signup-links">
          <a href="http://localhost:8080/jobs/candidate/signup">Signup as candidate</a>
        </div>
      </div>
      <div class="login-block">
        <h1>Hop back in</h1>
        <%--@elvariable id="user" type="com.example.jobportal.pojo.User"--%>
        <form:form action="/jobs/login/" method="post" modelAttribute="user">
          <div>
            <form:label for="email" path="email">Email:</form:label>
            <form:input type="text" id="email" name="email"  path="email"/> <form:errors cssClass="formErrors" path="email"/>
          </div>
          <div>
            <form:label for="password" path="password">Password:</form:label>
            <form:input type="password" id="password" name="password"  path="password"/> <form:errors cssClass="formErrors" path="password"/>
          </div>
          <input type="submit" value="Login" />
        </form:form>
      </div>
    </div>
  </body>
</html>
