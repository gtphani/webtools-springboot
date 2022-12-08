<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Update Job Posting</title>
    <link href="<c:url value="/static-resources/css/update-job-posting.css" />" rel="stylesheet">
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
      <div class="update-job-block">
        <%--@elvariable id="jobPosting" type="com.example.jobportal.pojo.JobPosting"--%>
        <form:form action="/jobs/recruiter/posting/${retrievedJobPosting.id}" method="post" modelAttribute="jobPosting">
          <div class="update-job-form">
            <div class="job-title-block">
              <form:label for="title" path="title">Job Title:</form:label>
              <form:input
                      type="text"
                      id="title"
                      value="${retrievedJobPosting.title}"
                      name="title"
                      path="title"
              />
            </div>
            <div class="job-location-block">
              <form:label for="location" path="location">Job Location:</form:label>
              <form:input type="text" id="location" value="${retrievedJobPosting.location}" name="location" path="location"/>
            </div>
            <div class="job-yoe-block">
              <form:label for="experience" path="">Experience Range:</form:label>
              <form:input type="text" id="experience" value="${retrievedJobPosting.experience}" name="experience" path="experience"/>
            </div>
            <div class="job-skills-block">
              <form:label for="skills" path="skills">Job Skills:</form:label>
              <form:input type="text" id="skills" value="${retrievedJobPosting.skills}" name="skills" path="skills"/>
            </div>
            <div class="job-description-block">
              <form:label for="description" path="description">Job Description:</form:label>
              <form:textarea type="text" id="description" name="description" path="description"/>
            </div>
            <input type="submit" value="Update Job" class="update-jobdetails">
            <div class="timestamp created-at">Creates at: ${retrievedJobPosting.createdAt}</div>
            <div class="timestamp updated-at">Updated at: ${retrievedJobPosting.updatedAt}</div>
          </div>
        </form:form>
        <div class="applicants-block">
          <div class="applications-received-label">Applications Received</div>
          <c:forEach var="application" items="${applications}">
            <div onclick="location.href='http://localhost:8080/jobs/posting/${retrievedJobPosting.id}/applications/${application.id}'" class="applicant-individual-block">
              <div class="profile-picture-block">
                <img src="data:image/jpeg;base64, ${application.candidate.user.base64AvatarFile}" alt="Candidate" />
              </div>
              <div class="applicant-name">${application.candidate.user.firstName} ${application.candidate.user.lastName}</div>
              <div class="applied-on">${application.applieddAt}</div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </body>
</html>
