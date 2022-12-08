package com.example.jobportal.validator;

import com.example.jobportal.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserSignupValidator implements Validator {

    private static final int MINIMUM_PASSWORD_LENGTH = 6;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty-name", "FirstName cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty-lastName", "LastName cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty-email", "Email cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "empty-confirmPassword", "Confirm password cannot be empty");

        User user = (User) target;
        if (user.getPassword() != null && user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "field.min.length", new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)}, "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }

        if (user.getPassword() != null && user.getConfirmPassword() !=null && !user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "field.min.length", "Passwords do not match");
        }
    }
}
