<!DOCTYPE html>
<html>
  <head>
    <title>Login/Signup</title>
    <link rel="stylesheet" href="/css/candidate-profile.css" />
  </head>
  <body>
    <div class="main-block">
      <div class="navigation-header">
        <div>
          <a class="active-tab" href="#home">Job List</a>
        </div>
        <div>
          <a href="#home">Applied</a>
        </div>
        <div>
          <a href="#home">Profile</a>
        </div>
        <div>
          <a href="#home">Logout</a>
        </div>
      </div>
      <form action="">
        <div class="info-block">
          <div class="name-block">
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" />
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" />
          </div>
          <div>Email: gtphani@gmail.com</div>
          <div class="phone-number-block">
            <label for="phonenumber">Phone number:</label>
            <input type="text" id="phonenumber" name="phonenumber" />
          </div>
          <div class="location-preference-block">
            <label for="location-preference-block">Location preference:</label>
            <input
              type="text"
              id="location-preference-block"
              name="location-preference-block"
            />
          </div>
          <div class="compensation-preference-block">
            <label for="compensation-preference-block"
              >Compensation preference:</label
            >
            <input
              type="text"
              id="compensation-preference-block"
              name="compensation-preference-block"
            />
          </div>
        </div>
        <div class="profile-picture-block">
          <img src="/logos/icons8-popeye-250.png" alt="Avatar" />
          <input type="file" id="prof-picture" name="prof-picture" />
        </div>
        <div class="update-profile-block">
          <button type="submit" class="update-profile">Update Profile</button>
        </div>
      </form>
    </div>
  </body>
</html>
