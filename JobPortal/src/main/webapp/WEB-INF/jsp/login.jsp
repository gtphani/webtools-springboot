<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" href="/login.css" />
  </head>
  <body>
    <div class="main-block">
      <div class="signup-block">
        <h1>Signup to explore new opportunities and open candidates</h1>
        <form action="" method="post">
          <div class="nameblock">
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" />
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" />
          </div>
          <div>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" />
          </div>
          <div>
            <label for="signup-password">Password:</label>
            <input
              type="password"
              id="signup-password"
              name="signup-password"
            />
          </div>
          <div>
            <label for="signup-confirm-password">Confirm Password:</label>
            <input
              type="password"
              id="signup-confirm-password"
              name="signup-confirm-password"
            />
          </div>
          <input type="submit" value="Sign up" />
        </form>
      </div>
      <div class="login-block">
        <h1>Hop back in</h1>
        <form action="">
          <div>
            <label for="email">Email:</label>
            <input type="text" id="loginemail" name="email" />
          </div>
          <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />
          </div>
          <input type="submit" value="Login" />
        </form>
      </div>
    </div>
  </body>
</html>
