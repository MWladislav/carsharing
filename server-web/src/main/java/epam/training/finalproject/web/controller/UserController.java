package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import epam.training.finalproject.model.service.interfaces.UserService;
import epam.training.finalproject.web.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarProfileService carProfileService;

    @GetMapping(value = "/users/id{id}")
    public User userPage(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal userPrincipal) throws Exception{
        User userFromRequest=userService.getById(id);
        if (userFromRequest==null){

        }

        return userService.getById(userPrincipal.getId());
    }

    @GetMapping(value = "/userPage/orders")
    public List<Order> getUserOrders(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){
        return userService.getById(userPrincipal.getId()).getOrders();
    }
}
