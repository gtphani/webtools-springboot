
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Job Detail Page</title>
    <link href="<c:url value="/static-resources/css/job-detail.css" />" rel="stylesheet">
  </head>
  <body>
    <div class="main-block">
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
      <div class="job-detail-block">
        <div class="job-detail-top">
          <div class="job-card-logo">
            <img src="data:image/jpeg;base64, ${retrievedJobPosting.company.base64logoFile}" alt="Mountain" />
          </div>
          <div class="job-title-company-location">
            <div class="job-title">${retrievedJobPosting.title}</div>
            <div class="job-company">${retrievedJobPosting.company.name}</div>
            <div class="job-location">${retrievedJobPosting.location}</div>
          </div>
          <div class="job-apply-button">
            <c:choose>
              <c:when test="${retrievedJobPosting.isApplied}">
                <div>
                  Status: <span class="status-display">${jobApplication.status}</span>
                </div>
                <div>
                  Applied at: ${jobApplication.applieddAt}
                </div>
                <div>
                  Updated at: ${jobApplication.updatedAt}
                </div>
                <div>
                  <a class="apply-form" href="http://localhost:8080/jobs/posting/${retrievedJobPosting.id}/applications/${jobApplication.id}">Go to Application</a>
                </div>
              </c:when>
              <c:otherwise>
                <form:form cssClass="apply-form" action="/jobs/candidate/opportunity/${retrievedJobPosting.id}/apply" method="post" enctype="multipart/form-data">
                  <label for="resume">Resume: </label>
                  <input type="file" id="resume" name="resume" required>
                  <input type="submit" class="job-apply" value="Apply">
                </form:form>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="job-skills-section">
          <div class="job-yoe">
            <strong>Experience required:</strong> ${retrievedJobPosting.experience}
          </div>
          <div class="job-skills">
            <strong>Skills:</strong> ${retrievedJobPosting.skills}
          </div>
        </div>
        <div class="job-description">
          ${retrievedJobPosting.description}
        </div>
      </div>
    </div>
  </body>
</html>
