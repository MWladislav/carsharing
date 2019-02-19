package epam.finalProject.carSharing.controller;

import epam.finalProject.carSharing.model.domain.entity.CustomUser;
import epam.finalProject.carSharing.model.domain.entity.User;
import epam.finalProject.carSharing.model.domain.entity.annotations.CurrentUser;
import epam.finalProject.carSharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(@CurrentUser CustomUser customUser,Model model) {
        User user= customUser.getCurrentUser();
        model.addAttribute("email",user.getEmail());
        model.addAttribute("username", user.getUsername());
        return "welcome";
    }


}
