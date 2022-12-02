<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Signup candidate</title>
    <link rel="stylesheet" href="/login.css" />
</head>
<body>
<div class="main-block">
    <div class="signup-block">
        <h1>Signup to explore new opportunities</h1>
        <form:form action="/candidate/signup" method="post" modelAttribute="">
            <div class="nameblock">
                <form:label for="firstname" path="firstname">First Name:</form:label>
                <form:input type="text" id="firstname" name="firstname" path="firstname"/>
                <form:label for="lastname" path="lastname">Last Name:</form:label>
                <form:input type="text" id="lastname" name="lastname" path="lastname"/>
            </div>
            <div>
                <form:label for="email" path="email">Email:</form:label>
                <form:input type="text" id="email" name="email" path="email"/>
            </div>
            <div>
                <form:label for="signup-password" path="signup-password">Password:</form:label>
                <form:input
                        type="password"
                        id="signup-password"
                        name="signup-password"
                        path="signup-password"
                />
            </div>
            <div>
                <form:label for="signup-confirm-password" path="signup-confirm-password">Confirm Password:</form:label>
                <form:input
                        type="password"
                        id="signup-confirm-password"
                        name="signup-confirm-password"
                        path="signup-confirm-password"
                />
            </div>
            <input type="submit" value="Sign up" />
        </form:form>
    </div>
</div>
</body>
</html>
