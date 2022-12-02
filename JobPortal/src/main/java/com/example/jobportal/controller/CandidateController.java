package com.example.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.example.jobportal.pojo.User;
import com.example.jobportal.dao.UserDAO;

@Controller
public class CandidateController {

    @GetMapping("/candidate/signup")
    public String addUserGet(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "signup-candidate";
    }

    @PostMapping("/candidate/signup")
    public String addUserPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, UserDAO userdao) {
        try {
            userdao.saveCandidate(user);
        } catch (Exception e) {
            System.out.println("User cannot be Added: " + e.getMessage());
        }

        status.setComplete();
        return "addedUser";
    }
}
