package com.example.jobportal.controller;

import com.example.jobportal.dao.JobPostingDAO;
import com.example.jobportal.pojo.CandidateProfile;
import com.example.jobportal.validator.UserSignupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.example.jobportal.pojo.User;
import com.example.jobportal.dao.UserDAO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CandidateController {

    private UserDAO userDAO;

    private UserSignupValidator userSignupValidator;

    private JobPostingDAO jobPostingDAO;

    public CandidateController() {

    }

    @Autowired
    public CandidateController(UserSignupValidator userSignupValidator, JobPostingDAO jobPostingDAO, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userSignupValidator = userSignupValidator;
        this.jobPostingDAO = jobPostingDAO;
    }

    @GetMapping("/candidate/signup")
    public String addUserGet(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "signup-candidate";
    }

    @PostMapping("/candidate/signup")
    public String addUserPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {
            userSignupValidator.validate(user, result);
            if (result.hasErrors()){
                return "signup-candidate";
            }
            if (userDAO.getUserByEmail(user.getEmail())!=null) {
                result.rejectValue("email", "invalid-signup", "Account with this email already exists!");
                return "signup-candidate";
            }
            user.setUserType(User.UserType.CANDIDATE);
            CandidateProfile candidateProfile = new CandidateProfile();
            candidateProfile.setUser(user);
            userDAO.saveCandidate(user, candidateProfile);
        } catch (Exception e) {
            System.out.println("User cannot be Added: " + e.getMessage());
        }
        request.getSession().setAttribute("loggedinUser", user.getEmail());
        status.setComplete();
        return "redirect:/candidate/opportunities";
    }

    @GetMapping("/candidate/opportunities")
    public String opportunitiesListGet(ModelMap model, User user,
                                       HttpServletRequest request) {
        model.addAttribute("user", user);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            CandidateProfile candidateProfile = userDAO.getUserByEmail(userEmail).getCandidateProfile();
            model.addAttribute("candidate", candidateProfile);
            model.addAttribute("jobsPosted", jobPostingDAO.getAvailableJobPostings(candidateProfile, null));
        } catch (Exception e) {
            System.out.println("Job list could not be retrieved: " + e.getMessage());
        }
        return "job-list";
    }

    @PostMapping("/candidate/opportunities/search")
    public String opportunitiesListPost(ModelMap model, User user, HttpServletRequest request) {
        model.addAttribute("user", user);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            CandidateProfile candidateProfile = userDAO.getUserByEmail(userEmail).getCandidateProfile();
            model.addAttribute("candidate", candidateProfile);
            model.addAttribute("jobsPosted", jobPostingDAO.getAvailableJobPostings(candidateProfile, request.getParameter("search")));
        } catch (Exception e) {
            System.out.println("Job list could not be retrieved: " + e.getMessage());
        }
        return "job-list";
    }

    @GetMapping("/candidate/applications")
    public String applicationsListGet(ModelMap model, User user, HttpServletRequest request) {
        model.addAttribute("user", user);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            CandidateProfile candidateProfile = userDAO.getUserByEmail(userEmail).getCandidateProfile();
            model.addAttribute("candidate", candidateProfile);
            model.addAttribute("jobsPosted", jobPostingDAO.getAppliedJobPostings(candidateProfile));
        } catch (Exception e) {
            System.out.println("Job list could not be retrieved: " + e.getMessage());
        }
        return "job-list";
    }

    @GetMapping("/candidate/profile")
    public String candidateProfileGet(ModelMap model, User user, HttpServletRequest request) {
        model.addAttribute("user", user);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            User loggedinUser = userDAO.getUserByEmail(userEmail);
            loggedinUser.setBase64AvatarFile();
            CandidateProfile candidateProfile = loggedinUser.getCandidateProfile();
            model.addAttribute("loggedinUser", loggedinUser);
            model.addAttribute("candidate", candidateProfile);
        } catch (Exception e) {
            System.out.println("Candidate profile could not be retrieved: " + e.getMessage());
        }
        return "candidate-profile";
    }

    @PostMapping("/candidate/profile")
    public String candidateProfilePost(@RequestParam("userAvatar") MultipartFile avatar, @ModelAttribute("user") User user,
                                       ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", user);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            User loggedinUser = userDAO.getUserByEmail(userEmail);
            CandidateProfile candidateProfile = loggedinUser.getCandidateProfile();

            loggedinUser.setFirstName(user.getFirstName());
            loggedinUser.setLastName(user.getLastName());

            if (!avatar.isEmpty()){loggedinUser.setAvatar(avatar.getBytes());}

            candidateProfile.setPhoneNumber(user.getCandidateProfile().getPhoneNumber());
            candidateProfile.setLinkedinURL(user.getCandidateProfile().getLinkedinURL());
            candidateProfile.setPreferredLocation(user.getCandidateProfile().getPreferredLocation());
            candidateProfile.setPreferredSalary(user.getCandidateProfile().getPreferredSalary());
            userDAO.updateCandidate(loggedinUser, candidateProfile);

            loggedinUser.setBase64AvatarFile();
            model.addAttribute("loggedinUser", loggedinUser);
            model.addAttribute("candidate", candidateProfile);
        } catch (Exception e) {
            System.out.println("Candidate profile could not be retrieved: " + e.getMessage());
        }
        return "candidate-profile";
    }
}
