<!DOCTYPE html>
<html>
  <head>
    <title>Login/Signup</title>
    <link rel="stylesheet" href="/css/job-posting.css" />
  </head>
  <body>
    <div class="main-block">
      <div class="navigation-header">
        <div>
          <a href="#home">Create new Job</a>
        </div>
        <div>
          <a href="#home">Posted Jobs</a>
        </div>
        <div>
          <a href="#home">Logout</a>
        </div>
      </div>
      <div class="update-job-block">
        <form action="">
          <div class="update-job-form">
            <div class="job-title-block">
              <label for="job-title">Job Title:</label>
              <input
                type="text"
                id="job-title"
                placeholder="Software Developer"
                name="job-title"
              />
            </div>
            <div class="job-location-block">
              <label for="job-location">Job Location:</label>
              <input type="text" id="job-location" name="job-location" />
            </div>
            <div class="job-yoe-block">
              <label for="job-yoe">Experience Range:</label>
              <input type="text" id="job-yoe" name="job-yoe" />
            </div>
            <div class="job-skills-block">
              <label for="job-skills">Job Skills:</label>
              <input type="text" id="job-skills" name="job-skills" />
            </div>
            <div class="job-description-block">
              <label for="job-description">Job Description:</label>
              <input type="text" id="job-description" name="job-description" />
            </div>
            <button type="submit" class="update-jobdetails">
              Update Job Details
            </button>
            <div class="timestamp created-at">Created at: 22nd Aug, 2022</div>
            <div class="timestamp updated-at">Updated at: 24nd Aug, 2022</div>
          </div>
        </form>
        <div class="applicants-block">
          <div class="applications-received-label">Applications Received</div>
          <div class="applicant-individual-block">
            <div class="profile-picture-block">
              <img src="/logos/icons8-popeye-250.png" alt="Avatar" />
            </div>
            <div class="applicant-name">Popoye, The Sailor</div>
            <div class="applied-on">26th Aug, 2022</div>
          </div>
          <div class="applicant-individual-block">
            <div class="profile-picture-block">
              <img src="/logos/icons8-popeye-250.png" alt="Avatar" />
            </div>
            <div class="applicant-name">Popoye, The Sailor</div>
            <div class="applied-on">26th Aug, 2022</div>
          </div>
          <div class="applicant-individual-block">
            <div class="profile-picture-block">
              <img src="/logos/icons8-popeye-250.png" alt="Avatar" />
            </div>
            <div class="applicant-name">Popoye, The Sailor</div>
            <div class="applied-on">26th Aug, 2022</div>
          </div>
          <div class="applicant-individual-block">
            <div class="profile-picture-block">
              <img src="/logos/icons8-popeye-250.png" alt="Avatar" />
            </div>
            <div class="applicant-name">Popoye, The Sailor</div>
            <div class="applied-on">26th Aug, 2022</div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
