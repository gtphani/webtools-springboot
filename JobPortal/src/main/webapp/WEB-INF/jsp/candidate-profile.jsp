<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Profile page</title>
    <link href="<c:url value="/static-resources/css/candidate-profile.css" />" rel="stylesheet">
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
      <%--@elvariable id="user" type="com.example.jobportal.pojo.User"--%>
      <form:form action="/jobs/candidate/profile" method="post" enctype="multipart/form-data" modelAttribute="user">
        <div class="info-block">
          <div class="name-block">
            <form:label for="firstName" path="firstName">First Name:</form:label>
            <form:input type="text" id="firstName" name="firstName" path="firstName" value="${loggedinUser.firstName}"/>
            <form:label for="lastName" path="lastName">Last Name:</form:label>
            <form:input type="text" id="lastName" name="lastName" path="lastName" value="${loggedinUser.lastName}"/>
          </div>
          <div>Email: ${loggedinUser.email}</div>
          <div class="phone-number-block">
            <form:label for="candidateProfile.phoneNumber" path="candidateProfile.phoneNumber">Phone number:</form:label>
            <form:input type="text" id="candidateProfile.phoneNumber" name="candidateProfile.phoneNumber" path="candidateProfile.phoneNumber" value="${candidate.phoneNumber}"/>
          </div>
          <div class="linkedin-block">
            <form:label for="candidateProfile.linkedinURL" path="candidateProfile.linkedinURL"
            >Linkedin:</form:label
            >
            <form:input
                    type="text"
                    id="candidateProfile.linkedinURL"
                    name="candidateProfile.linkedinURL"
                    path="candidateProfile.linkedinURL"
                    value="${candidate.linkedinURL}"
            />
          </div>
          <div class="location-preference-block">
            <form:label for="candidateProfile.preferredLocation" path="candidateProfile.preferredLocation">Location preference:</form:label>
            <form:input
              type="text"
              id="candidateProfile.preferredLocation"
              name="candidateProfile.preferredLocation"
              path="candidateProfile.preferredLocation"
              value="${candidate.preferredLocation}"
            />
          </div>
          <div class="compensation-preference-block">
            <form:label for="candidateProfile.preferredSalary" path="candidateProfile.preferredSalary"
              >Compensation preference:</form:label
            >
            <form:input
              type="text"
              id="candidateProfile.preferredSalary"
              name="candidateProfile.preferredSalary"
              path="candidateProfile.preferredSalary"
              value="${candidate.preferredSalary}"
            />
          </div>
        </div>
        <div class="profile-picture-block">
          <img alt="Avatar" src="data:image/jpeg;base64, ${loggedinUser.base64AvatarFile}"/>
          <input type="file" id="userAvatar" name="userAvatar"/>
        </div>
        <div class="update-profile-block">
          <input type="submit" class="update-profile" value="Update Profile">
        </div>
      </form:form>
    </div>
  </body>
</html>
