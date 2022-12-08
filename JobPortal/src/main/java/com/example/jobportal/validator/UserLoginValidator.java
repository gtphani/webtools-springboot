package com.example.jobportal.validator;

import com.example.jobportal.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty-email", "Please enter email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Please enter password");
    }
}
