package epam.finalProject.carSharing.validator;


import epam.finalProject.carSharing.model.domain.entity.UserFormParams;
import epam.finalProject.carSharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserFormParams.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserFormParams userForm = (UserFormParams) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (userForm.getUsername().length() < 4 || userForm.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        //if (userService.findByUsername(userForm.getUsername()) != null) {
        //    errors.rejectValue("username", "Duplicate.userForm.username");
        //}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");
        if (userForm.getPassword().length() < 4 || userForm.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}