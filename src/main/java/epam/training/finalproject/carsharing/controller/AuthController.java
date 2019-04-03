package epam.training.finalproject.carsharing.controller;

import epam.training.finalproject.carsharing.model.domain.entity.User;
import epam.training.finalproject.carsharing.model.domain.entity.UserFormParams;
import epam.training.finalproject.carsharing.model.service.interfaces.UserService;
import epam.training.finalproject.carsharing.validator.UserRegistrationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationValidator validator;

    private static final Logger LOGGER = Logger.getLogger(AuthController.class);

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public Long register(User newUser) {
//        try {
//            return userService.save(newUser);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return 0L;
//        }
//    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userFormParams", new UserFormParams());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userFormParams") UserFormParams userFormParams, BindingResult bindingResult, Model model) {
        validator.validate(userFormParams, bindingResult);
        User user=new User();
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        user.setEmail(userFormParams.getEmail());
        user.setUsername(userFormParams.getUsername());
        user.setPassword(userFormParams.getPassword());

        userService.save(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "");
        }
        return "login";
    }


}