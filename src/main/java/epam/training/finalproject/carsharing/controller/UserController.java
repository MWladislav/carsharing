package epam.training.finalproject.carsharing.controller;

import epam.training.finalproject.carsharing.model.domain.entity.Car;
import epam.training.finalproject.carsharing.model.domain.entity.Order;
import epam.training.finalproject.carsharing.model.domain.entity.User;
import epam.training.finalproject.carsharing.model.domain.entity.UserDetailsImpl;
import epam.training.finalproject.carsharing.model.service.interfaces.CarService;
import epam.training.finalproject.carsharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @GetMapping(value = "/userPage")
    public String userPage(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) throws Exception{
//        User test=userService.findByUsername("x");
        User user=userService.getByIdWithOrders(userDetailsImpl.getId());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role",user.getRole());
        Order order=user.getOrders().get(0);
        model.addAttribute("price",order.getPrice());
        model.addAttribute("status",order.getStatus().toString().toLowerCase());
        Car car=carService.getById(order.getCarId());
        model.addAttribute("car",car);
        return "userPage";
    }

    @GetMapping(value = "/userPage/orders")
    public String getUserOrders(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model){
        User user=userService.getByIdWithOrdersAndAdInfo(userDetailsImpl.getId());
        List<Order> orders=user.getOrders();
        model.addAttribute("orders",orders);
        return "userOrders";
    }
}
