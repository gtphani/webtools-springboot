package com.example.jobportal.controller;

import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.pojo.User;
import com.example.jobportal.validator.UserLoginValidator;
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
public class LoginController {

    UserDAO userDAO;

    UserLoginValidator loginValidator;

    public LoginController() {
    }

    @Autowired
    public LoginController(UserDAO userDAO, UserLoginValidator loginValidator) {
        this.userDAO = userDAO;
        this.loginValidator = loginValidator;
    }

    @GetMapping("/login")
    public String loginGet(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpServletRequest request) {
        String redirectPage = "";
        try {
            loginValidator.validate(user, result);
            if (result.hasErrors()) return "login";
            User loggedInUser = userDAO.getUserByCredentials(user.getEmail(), user.getPassword());
            if (loggedInUser == null) {
                result.rejectValue("password", "invalid-login", "Invalid username/password!");
                return "login";
            }
            if (loggedInUser.getUserType().equals(User.UserType.ADMIN)) {
                redirectPage = "redirect:/admin/company-list";
            }  else if (loggedInUser.getUserType().equals(User.UserType.RECRUITER)) {
                redirectPage = "redirect:/recruiter/posted-jobs";
            } else if (loggedInUser.getUserType().equals(User.UserType.CANDIDATE)) {
                redirectPage = "redirect:/candidate/opportunities";
            }
            request.getSession().setAttribute("loggedinUser", loggedInUser.getEmail());
            status.setComplete();
        } catch (Exception e) {
            System.out.println("Login failed with error: " + e.getMessage());
        }
        return redirectPage;
    }

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request, SessionStatus status) {
        status.setComplete();
        request.getSession().removeAttribute("loggedinUser");
        return "redirect:/login";
    }
}
