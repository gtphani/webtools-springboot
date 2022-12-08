<%--
  Created by IntelliJ IDEA.
  User: phaniginjupalli
  Date: 12/3/22
  Time: 5:46 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create job posting</title>
    <link href="<c:url value="/static-resources/css/create-job-posting.css" />" rel="stylesheet">
</head>
<body>
<div class="main-block">
    <div class="navigation-header">
        <div class="search-jobs-label">
            <h1>${recruiter.user.firstName}, Manage job opportunities at ${recruiter.company.name}</h1>
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
    <div class="create-job-block">
        <%--@elvariable id="jobPosting" type="com.example.jobportal.pojo.JobPosting"--%>
        <form:form action="/jobs/recruiter/create-job" method="post" modelAttribute="jobPosting">
            <div class="create-job-form">
                <div class="job-title-block">
                    <form:label for="title" path="title">Job Title:</form:label>
                    <form:input
                            type="text"
                            id="title"
                            placeholder="Software Developer"
                            name="title"
                            path="title"
                    />
                </div>
                <div class="job-location-block">
                    <form:label for="location" path="location">Job Location:</form:label>
                    <form:input type="text" id="location" name="location" path="location"/>
                </div>
                <div class="job-yoe-block">
                    <form:label for="experience" path="">Experience Range:</form:label>
                    <form:input type="text" id="experience" name="experience" path="experience"/>
                </div>
                <div class="job-skills-block">
                    <form:label for="skills" path="skills">Job Skills:</form:label>
                    <form:input type="text" id="skills" name="skills" path="skills"/>
                </div>
                <div class="job-description-block">
                    <form:label for="description" path="description">Job Description:</form:label>
                    <form:textarea type="text" id="description" name="description" path="description"/>
                </div>
                <input type="submit" value="Create Job" class="create-jobdetails">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
