package com.example.jobportal.controller;

import com.example.jobportal.dao.CompanyDAO;
import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.pojo.Company;
import com.example.jobportal.pojo.User;
import com.example.jobportal.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CompanyController {

    CompanyValidator companyValidator;

    CompanyDAO companyDAO;

    UserDAO userDAO;

    public CompanyController() {
    }

    @Autowired
    public CompanyController(CompanyValidator companyValidator, UserDAO userDAO, CompanyDAO companyDAO) {
        this.companyValidator = companyValidator;
        this.companyDAO = companyDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/admin/company-list")
    public String companyListGet(ModelMap model, HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            User user = userDAO.getUserByEmail(userEmail);
            if (!user.getUserType().equals(User.UserType.ADMIN)) return "redirect:/login";
            List<Company> companies = companyDAO.getCompanies();
            for (Company company: companies) {
                company.setBase64logoFile();
            }
            model.addAttribute("companies", companies);
        } catch (Exception e) {
            System.out.println("Company list could not be retrieved: " + e.getMessage());
        }
        model.addAttribute("testKey", "testValue");
        return "company-list";
    }

    @GetMapping("/admin/onboard")
    public String onboardCompanyGet(ModelMap model, Company company, HttpServletRequest request) {
        model.addAttribute("company", company);
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            User user = userDAO.getUserByEmail(userEmail);
            if (!user.getUserType().equals(User.UserType.ADMIN)) return "redirect:/login";
            return "add-company";
        } catch (Exception e) {
            System.out.println("Error in admin anboard get: " + e.getMessage());
        }
        return "error-500";
    }

    @PostMapping("/admin/onboard")
    public String onboardCompanyPost(@RequestParam("logo") MultipartFile logo, @RequestParam("name") String name,
                                     SessionStatus status,  HttpServletRequest request) {
        try {
            String userEmail = (String) request.getSession().getAttribute("loggedinUser");
            if (userEmail == null) return "redirect:/login";
            User user = userDAO.getUserByEmail(userEmail);
            if (!user.getUserType().equals(User.UserType.ADMIN)) return "redirect:/login";
            Company company = new Company();
            company.setName(name);
            System.out.println(logo.getOriginalFilename());
            company.setLogo(logo.getBytes());
//            companyValidator.validate(company);
//            if(result.hasErrors())
//                return "add-company";

                companyDAO.saveCompany(company);
        } catch (Exception e) {
            System.out.println("Company cannot be added: " + e.getMessage());
        }
        status.setComplete();
        return "redirect:/admin/company-list";
    }
}
