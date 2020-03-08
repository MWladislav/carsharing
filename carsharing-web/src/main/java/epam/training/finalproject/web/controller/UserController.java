package epam.training.finalproject.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping(value = "/profile")
//    public ResponseEntity<User> getUserProfile(@RequestParam Long id){
//        return ResponseEntity.ok(userService.getById(id));
//    }
//
//    @GetMapping("/orders")
//    public ResponseEntity<List<Order>> getUserOrders(@RequestParam Long userId){
//        return ResponseEntity.ok(orderService.findOrdersByUserId(userId));
//    }
//
//    @Secured(value = "ROLE_ADMIN")
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok(userService.getAll());
//    }
//
//    @PostMapping("/updateProfile")
//    public ResponseEntity<Long> updateUserProfile(@RequestBody User user){
//        return ResponseEntity.ok(userService.update(user));
//    }
//
//    @DeleteMapping("/deleteProfile")
//    public ResponseEntity<Long> deleteUserProfile(@RequestBody User user){
//        return ResponseEntity.ok(userService.delete(user));
//    }
//
//    @Secured(value = "ROLE_ADMIN")
//    @PostMapping("/banUser")
//    public ResponseEntity<Long> banUser(@RequestBody User user){
//        return ResponseEntity.ok(userService.banUser(user));
//    }
}
