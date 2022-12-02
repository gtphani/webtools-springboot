<%--
  Created by IntelliJ IDEA.
  User: phaniginjupalli
  Date: 12/1/22
  Time: 4:48 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login/Signup</title>
    <link href="<c:url value="/static-resources/css/job-list.css" />" rel="stylesheet">
</head>
<body>
<div class="main-block">
    <div class="navigation-header">
        <div class="search-jobs-label">
            <h1>Explore opportunities</h1>
        </div>
        <div>
            <form action="">
                <input type="text" placeholder="Job Title" name="search" />
                <button type="submit" class="search-button">Search</button>
            </form>
        </div>
        <div>
            <a class="active-tab" href="#home">Job List</a>
        </div>
        <div>
            <a href="http://localhost:8080/jobs/recruiter/create-job">Create New Job</a>
        </div>
        <div>
            <a href="http://localhost:8080/jobs/recruiter/posted-jobs">Posted Jobs</a>
        </div>
        <div>
            <a href="http://localhost:8080/jobs/logout">Logout</a>
        </div>
    </div>
    <%--@elvariable id="company" type="com.example.jobportal.pojo.JobPosting"--%>
    <div class="job-list">
        <c:forEach var="job" items="${jobsPosted}">
            <div onclick="location.href='http://localhost:8080/jobs/detail/${job.id}'" class="job-card">
                <div class="job-card-logo">
                    <img src="data:image/jpeg;base64, ${job.company.base64logoFile}" alt="Mountain" />
                </div>
                <div class="job-card-basic-info">
                    <div class="job-card-title">${job.title}</div>
                    <div class="job-card-company">${job.company.name}</div>
                    <div class="job-card-location">${job.location}</div>
                </div>
                <div class="job-card-extra-info">
                    <div class="job-card-yoe">${job.experience}</div>
                    <div class="job-card-skills">${job.skills}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

