package com.example.jobportal.validator;

import com.example.jobportal.pojo.Company;
import com.example.jobportal.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CompanyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty-name", "Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "logo", "empty-logo", "Logo is not selected");

    }

}
