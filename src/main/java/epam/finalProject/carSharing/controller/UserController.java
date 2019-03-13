package epam.finalProject.carSharing.controller;

import epam.finalProject.carSharing.model.domain.entity.Car;
import epam.finalProject.carSharing.model.domain.entity.Order;
import epam.finalProject.carSharing.model.domain.entity.User;
import epam.finalProject.carSharing.model.domain.entity.UserDetailsImpl;
import epam.finalProject.carSharing.model.service.interfaces.CarService;
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
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        User user=userService.getByIdWithOrders(userDetailsImpl.getId());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role",user.getRole());
        Order order=user.getOrders().get(0);
        model.addAttribute("price",order.getPrice());
        model.addAttribute("status",order.getStatus());
        Car car=carService.getById(order.getCarId());
        model.addAttribute("car",car);
        return "userPage";
    }


}
