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
</head>
<body>
<div class="job-communication">
    <div class="communication-title">
        Application related communications
    </div>
    <div class="chat-box">
        <div class="chat-box-inner">
            <div class="message-sender">
                <img src="/logos/icons8-penguin-96.png" alt="Avatar" />
                <p>Hello. How are you today?</p>
                <span class="time-stamp">11:00</span>
            </div>
            <div class="message-receiver">
                <img src="/logos/icons8-owl-96.png" alt="Avatar" />
                <p>Hello. I'm good</p>
                <span class="time-stamp">11:02</span>
            </div>
            <div class="message-sender">
                <img src="/logos/icons8-penguin-96.png" alt="Avatar" />
                <p>Hello. How are you today?</p>
                <span class="time-stamp">11:03</span>
            </div>
            <div class="message-receiver">
                <img src="/logos/icons8-owl-96.png" alt="Avatar" />
                <p>Hello. I'm good</p>
                <span class="time-stamp">11:04</span>
            </div>
            <div class="message-sender">
                <img src="/logos/icons8-penguin-96.png" alt="Avatar" />
                <p>Hello. How are you today?</p>
                <span class="time-stamp">11:05</span>
            </div>
            <div class="message-receiver">
                <img src="/logos/icons8-owl-96.png" alt="Avatar" />
                <p>Hello. I'm good</p>
                <span class="time-stamp">11:06</span>
            </div>
        </div>
        <div class="send-message-div">
            <form action="">
                <input type="text" class="send-message-text" name="chat" />
                <button type="submit" class="send-message-button">Send</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
