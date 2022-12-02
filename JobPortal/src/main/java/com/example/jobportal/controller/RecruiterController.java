package com.example.jobportal.controller;

import com.example.jobportal.dao.CompanyDAO;
import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.pojo.RecruiterProfile;
import com.example.jobportal.pojo.User;
import com.example.jobportal.validator.RecruiterProfileValidator;
import com.example.jobportal.validator.UserSignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RecruiterController {

    CompanyDAO companyDAO;

    UserSignupValidator userSignupValidator;

    RecruiterProfileValidator recruiterProfileValidator;

    UserDAO userDAO;

    public RecruiterController() {

    }

    @Autowired
    public RecruiterController(CompanyDAO companyDAO, UserSignupValidator userSignupValidator, UserDAO userDAO,
                               RecruiterProfileValidator recruiterProfileValidator) {
        this.companyDAO = companyDAO;
        this.userSignupValidator = userSignupValidator;
        this.userDAO = userDAO;
        this.recruiterProfileValidator = recruiterProfileValidator;
    }

    @GetMapping("/recruiter/posted-jobs")
    public String getPostedJobs(ModelMap model, HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            RecruiterProfile recruiterProfile = userDAO.getUserByEmail(userEmail).getRecruiterProfile();
            model.addAttribute("jobsPosted", recruiterProfile.getJobsPosted());
        } catch (Exception e) {
            System.out.println("Job list could not be retrieved: " + e.getMessage());
        }
        return "signup-recruiter";
    }

    @GetMapping("/recruiter/signup")
    public String signupGet(ModelMap model, User user) {
        model.addAttribute("user", user);
        try {
            model.addAttribute("companies", companyDAO.getCompanies());
        } catch (Exception e) {
            System.out.println("Company list could not be retrieved: " + e.getMessage());
        }
        return "signup-recruiter";
    }

    @PostMapping("/recruiter/signup")
    public String signupPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {
            userSignupValidator.validate(user, result);
            if (result.hasErrors()) return "signup-recruiter";
            user.setUserType(User.UserType.RECRUITER);
            RecruiterProfile recruiterProfile = new RecruiterProfile();
            userDAO.saveRecruiter(user, recruiterProfile);
        } catch (Exception e) {
            System.out.println("User cannot be Added: " + e.getMessage());
        }
        request.getSession().setAttribute("loggedinUser", user.getEmail());
        status.setComplete();
        return "redirect:/recruiter/posted-jobs";
    }
}
