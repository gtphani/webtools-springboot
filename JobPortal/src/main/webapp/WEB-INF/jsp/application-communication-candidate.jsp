<%--
  Created by IntelliJ IDEA.
  User: phaniginjupalli
  Date: 12/3/22
  Time: 5:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Application Communication</title>
    <link href="<c:url value="/static-resources/css/application-communication.css" />" rel="stylesheet">
</head>
<body>
<div class="navigation-header">
    <div>
        <a href="http://localhost:8080/jobs/candidate/opportunities">Job List</a>
    </div>
    <div>
        <a href="http://localhost:8080/jobs/candidate/applications">Applied</a>
    </div>
    <div>
        <a href="http://localhost:8080/jobs/candidate/profile">Profile</a>
    </div>
    <div>
        <a href="http://localhost:8080/jobs/logout">Logout</a>
    </div>
</div>
<div class="communication-title">
    Your application for ${jobApplication.job.title}, ${jobApplication.job.company.name}
</div>
<div class="application-details">
    <div class="application-status">
        <div>Current Status: <span class="status-display">${jobApplication.status}</span></div>
    </div>
</div>
<div class="job-communication">
    <div class="chat-box">
        <div class="chat-box-inner">
            <c:forEach var="message" items="${messages}">
                <c:choose>
                    <c:when test="${message.sender.email == loggedinUser.email}">
                        <div class="message-loggedinUser">
                            <img src="data:image/jpeg;base64, ${message.sender.base64AvatarFile}" alt="Sender" />
                            <p><strong>${message.message}</strong></p>
                            <span class="time-stamp">${message.sentAt}</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="message-others">
                            <img src="data:image/jpeg;base64, ${message.sender.base64AvatarFile}" alt="Sender" />
                            <p><strong>${message.message}</strong></p>
                            <span class="time-stamp">${message.sentAt}</span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <div class="send-message-div">
            <form action="/jobs/application/${jobApplication.id}/message" method="post">
                <input type="text" class="send-message-text" name="chat-message" id="chat-message"/>
                <button type="submit" class="send-message-button">Send</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>