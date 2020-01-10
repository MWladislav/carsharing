package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.OrderService;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrders(@RequestParam Long userId){
        return ResponseEntity.ok(orderService.findOrdersByUserId(userId));
    }

}
