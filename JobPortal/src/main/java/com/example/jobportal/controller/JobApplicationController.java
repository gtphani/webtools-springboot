package com.example.jobportal.controller;

import com.example.jobportal.dao.ApplicationMessageDAO;
import com.example.jobportal.dao.JobApplicationDAO;
import com.example.jobportal.dao.JobPostingDAO;
import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.pojo.ApplicationMessage;
import com.example.jobportal.pojo.JobApplication;
import com.example.jobportal.pojo.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class JobApplicationController {

    JobApplicationDAO jobApplicationDAO;

    JobPostingDAO jobPostingDAO;

    ApplicationMessageDAO applicationMessageDAO;

    UserDAO userDAO;

    public JobApplicationController(JobPostingDAO jobPostingDAO, JobApplicationDAO jobApplicationDAO,
                                    ApplicationMessageDAO applicationMessageDAO, UserDAO userDAO) {
        this.jobApplicationDAO = jobApplicationDAO;
        this.jobPostingDAO = jobPostingDAO;
        this.applicationMessageDAO = applicationMessageDAO;
        this.userDAO = userDAO;
    }


    @GetMapping("/posting/{jobPostingId}/applications/{applicationId}")
    public String jobApplicationGet(@PathVariable String jobPostingId, @PathVariable String applicationId,
                                    ModelMap model, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("loggedinUser");
        if (userEmail == null) return "redirect:/login";
        String redirectPage = "";
        try {
            User user = userDAO.getUserByEmail(userEmail);
            JobApplication jobApplication = jobApplicationDAO.getApplicationById(Long.parseLong(applicationId));
            if (user.getUserType().equals(User.UserType.CANDIDATE)) {
                redirectPage = "application-communication-candidate";
            } else {
                redirectPage = "application-communication-recruiter";
            }
            model.addAttribute("jobApplication", jobApplication);
            model.addAttribute("loggedinUser", user);
            model.addAttribute("messages", jobApplication.getMessages());
        } catch (Exception e) {
            System.out.println("Could not retrieve application page " + e.getMessage());
        }
        return redirectPage;
    }

    @PostMapping("/application/{applicationId}/statusupdate")
    public String applicationStatusUpdate(@PathVariable String applicationId, ModelMap model, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("loggedinUser");
        if (userEmail == null) return "redirect:/login";
        String redirectPage = "";
        try {
            User user = userDAO.getUserByEmail(userEmail);
            JobApplication jobApplication = jobApplicationDAO.getApplicationById(Long.parseLong(applicationId));
            String newStatus = request.getParameter("status");
            jobApplication.setStatus(JobApplication.Status.valueOf(newStatus));
            jobApplicationDAO.updateApplication(jobApplication);

            model.addAttribute("jobApplication", jobApplication);
            model.addAttribute("loggedinUser", user);
            model.addAttribute("messages", jobApplication.getMessages());
            redirectPage = "redirect:/posting/"+jobApplication.getJob().getId()+"/applications/"+jobApplication.getId();
        } catch (Exception e) {
            System.out.println("Could not update status " + e.getMessage());
        }
        return redirectPage;
    }


    @PostMapping("/application/{applicationId}/message")
    public String applicationMessage(@PathVariable String applicationId, ModelMap model, HttpServletRequest request) {
        String userEmail = (String) request.getSession().getAttribute("loggedinUser");
        if (userEmail == null) return "redirect:/login";
        String redirectPage = "";
        try {
            User user = userDAO.getUserByEmail(userEmail);
            JobApplication jobApplication = jobApplicationDAO.getApplicationById(Long.parseLong(applicationId));
            String message = request.getParameter("chat-message");

            if (message != null) {
                ApplicationMessage applicationMessage = new ApplicationMessage();
                applicationMessage.setSender(user);
                applicationMessage.setMessage(message);
                applicationMessage.setApplication(jobApplication);
                applicationMessageDAO.saveMessage(applicationMessage);
            }
            redirectPage = "redirect:/posting/"+jobApplication.getJob().getId()+"/applications/"+jobApplication.getId();
            model.addAttribute("jobApplication", jobApplication);
            model.addAttribute("loggedinUser", user);
            model.addAttribute("messages", jobApplication.getMessages());
        } catch (Exception e) {
            System.out.println("Could not save message " + e.getMessage());
        }
        return redirectPage;
    }

    @GetMapping("/application/{applicationId}/resume")
    public void getResume(@PathVariable String applicationId, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            JobApplication jobApplication = jobApplicationDAO.getApplicationById(Long.parseLong(applicationId));
            String fileName = jobApplication.getCandidate().getUser().getResumeName();
            FileUtils.writeByteArrayToFile(new File("temp_resume.pdf"), jobApplication.getResume());
            File file = new File("temp_resume.pdf");
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            System.out.println("Could not save message " + e.getMessage());
        }
    }
}
