package com.example.jobportal.validator;

import com.example.jobportal.pojo.RecruiterProfile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RecruiterProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RecruiterProfile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "empty-company", "Please select your company");
    }
}
