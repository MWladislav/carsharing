package epam.finalProject.carSharing.controller;

import epam.finalProject.carSharing.model.domain.entity.UserDetailsImpl;
import epam.finalProject.carSharing.model.domain.entity.User;
import epam.finalProject.carSharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        User user= userDetailsImpl.getUser();
        model.addAttribute("email",user.getEmail());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role",user.getRole());
        return "welcome";
    }


}
