package epam.training.finalproject.validator;

import epam.training.finalproject.model.domain.entity.UserFormParams;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserFormParams.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserFormParams userForm = (UserFormParams) o;

    }
}
