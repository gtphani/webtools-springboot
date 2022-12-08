package com.example.jobportal.controller;

import com.example.jobportal.dao.JobApplicationDAO;
import com.example.jobportal.dao.JobPostingDAO;
import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.pojo.CandidateProfile;
import com.example.jobportal.pojo.JobApplication;
import com.example.jobportal.pojo.JobPosting;
import com.example.jobportal.pojo.RecruiterProfile;
import com.example.jobportal.validator.JobPostingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JobPostingController {

    JobPostingDAO jobPostingDAO;

    JobApplicationDAO jobApplicationDAO;

    JobPostingValidator jobPostingValidator;

    UserDAO userDAO;

    public JobPostingController() {}

    @Autowired
    public JobPostingController(JobPostingDAO jobPostingDAO, JobPostingValidator jobPostingValidator, UserDAO userDAO,
                                JobApplicationDAO jobApplicationDAO) {
        this.jobPostingDAO = jobPostingDAO;
        this.jobPostingValidator = jobPostingValidator;
        this.userDAO = userDAO;
        this.jobApplicationDAO = jobApplicationDAO;
    }

    @GetMapping("/recruiter/create-job")
    public String createJobGet(ModelMap model, JobPosting jobPosting, HttpServletRequest request) {
        model.addAttribute("jobPosting", jobPosting);
        String userEmail = (String) request.getSession().getAttribute("loggedinUser");
        if (userEmail == null) return "redirect:/login";
        try {
            RecruiterProfile recruiterProfile = userDAO.getUserByEmail(userEmail).getRecruiterProfile();
            model.addAttribute("recruiter", recruiterProfile);
        } catch (Exception e) {
            System.out.println("Could not retrieve user details on job creation page" + e.getMessage());
        }
        return "create-job-posting";
    }

    @PostMapping("/recruiter/create-job")
    public String createJobPost(@ModelAttribute("jobPosting") JobPosting jobPosting, BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            RecruiterProfile recruiterProfile = userDAO.getUserByEmail(userEmail).getRecruiterProfile();
            jobPosting.setRecruiter(recruiterProfile);
            jobPosting.setCompany(recruiterProfile.getCompany());

            jobPostingValidator.validate(jobPosting, result);
            if (result.hasErrors()) {
                return "create-job-posting";
            }
            jobPostingDAO.saveJobPosting(jobPosting);
        } catch (Exception e) {
            System.out.println("Job Posting could not be : " + e.getMessage());
        }
        status.setComplete();
        return "redirect:/recruiter/posted-jobs";
    }

    @GetMapping("/recruiter/posting/{jobPostingId}")
    public String jobDetailGet(@PathVariable String jobPostingId, ModelMap model, JobPosting jobPosting,
                               HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            RecruiterProfile recruiterProfile = userDAO.getUserByEmail(userEmail).getRecruiterProfile();
            JobPosting retrievedJobPosting = jobPostingDAO.getJobPostingByRecruiterId(Integer.parseInt(jobPostingId), recruiterProfile.getId());
            if (retrievedJobPosting == null) {
                return "error-404";
            }
            model.addAttribute("recruiter", recruiterProfile);
            model.addAttribute("retrievedJobPosting", retrievedJobPosting);
            model.addAttribute("applications", retrievedJobPosting.getApplicantList());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JobPosting retrival failed with error " + e.getMessage());
        }
        return "update-job-posting";
    }

    @PostMapping("/recruiter/posting/{jobPostingId}")
    public String jobDetailsPost(@PathVariable String jobPostingId, @ModelAttribute("jobPosting") JobPosting jobPosting, BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            RecruiterProfile recruiterProfile = userDAO.getUserByEmail(userEmail).getRecruiterProfile();
            JobPosting retrievedJobPosting = jobPostingDAO.getJobPostingByRecruiterId(Integer.parseInt(jobPostingId), recruiterProfile.getId());
            if (retrievedJobPosting == null) {
                return "error-404";
            }

            retrievedJobPosting.setTitle(jobPosting.getTitle());
            retrievedJobPosting.setSkills(jobPosting.getSkills());
            retrievedJobPosting.setLocation(jobPosting.getLocation());
            retrievedJobPosting.setExperience(jobPosting.getExperience());
            retrievedJobPosting.setDescription(jobPosting.getDescription());
            jobPostingDAO.updateJobPosting(retrievedJobPosting);

        } catch (Exception e) {
            System.out.println("Failed to update JobPosting " + jobPostingId + " with error" + e.getMessage());
        }
        return "redirect:/recruiter/posted-jobs";
    }

    @GetMapping("/candidate/opportunity/{jobPostingId}")
    public String jobOpportunityGet(@PathVariable String jobPostingId, ModelMap model, JobPosting jobPosting,
                                    HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            CandidateProfile candidateProfile = userDAO.getUserByEmail(userEmail).getCandidateProfile();
            JobPosting retrievedJobPosting = jobPostingDAO.getJobPostingById(Integer.parseInt(jobPostingId));
            if (retrievedJobPosting == null) {
                return "error-404";
            }

            retrievedJobPosting.getCompany().setBase64logoFile();
            JobApplication jobApplication = null;
            for (JobApplication application: candidateProfile.getApplicationList()) {
                if (application.getJob().getId() == retrievedJobPosting.getId()) {
                    retrievedJobPosting.setIsApplied(true);
                    jobApplication = application;
                }
                else {
                    retrievedJobPosting.setIsApplied(false);
                }
            }
//            retrievedJobPosting.setApplied(candidateProfile.getApplicationList().stream().anyMatch(a -> a.getJob().getId() == retrievedJobPosting.getId()));
            model.addAttribute("candidate", candidateProfile);
            model.addAttribute("retrievedJobPosting", retrievedJobPosting);
            model.addAttribute("jobApplication", jobApplication);
        } catch (Exception e) {
            System.out.println("JobPosting retrival failed with error " + e.getMessage());
        }
        return "job-detail";
    }

    @PostMapping("/candidate/opportunity/{jobPostingId}/apply")
    public String jobOpportunityApply(@PathVariable String jobPostingId, @RequestParam("resume") MultipartFile resumeFile,
                                      ModelMap model, SessionStatus status, HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            CandidateProfile candidateProfile = userDAO.getUserByEmail(userEmail).getCandidateProfile();
            JobPosting retrievedJobPosting = jobPostingDAO.getJobPostingById(Integer.parseInt(jobPostingId));
            if (retrievedJobPosting == null) {
                return "error-404";
            }
            retrievedJobPosting.getCompany().setBase64logoFile();
            retrievedJobPosting.setIsApplied(true);

            JobApplication jobApplication = new JobApplication();
            jobApplication.setCandidate(candidateProfile);
            jobApplication.setJob(retrievedJobPosting);
            jobApplication.setStatus(JobApplication.Status.APPLIED);
            jobApplication.setResume(resumeFile.getBytes());
            jobApplicationDAO.saveApplication(jobApplication);

            model.addAttribute("candidate", candidateProfile);
            model.addAttribute("retrievedJobPosting", retrievedJobPosting);
            model.addAttribute("jobApplication", jobApplication);

        } catch (Exception e) {
            System.out.println("Job Application failed for job " + jobPostingId);
        }
        status.setComplete();
        return "job-detail";
    }
}
