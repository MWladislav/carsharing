package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.OrderService;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<Long> updateUserProfile(@RequestBody User user){
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/deleteProfile")
    public ResponseEntity<Long> deleteUserProfile(@RequestBody User user){
        return ResponseEntity.ok(userService.delete(user));
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping("/banUser")
    public ResponseEntity<Long> banUser(@RequestBody User user){
        return ResponseEntity.ok(userService.banUser(user));
    }
}
