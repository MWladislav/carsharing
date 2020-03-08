package epam.training.finalproject.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {

//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping("/general-info")
//    public ResponseEntity<Order> getOrder(@RequestParam Long id) {
//        return ResponseEntity.ok(orderService.getById(id));
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Order>> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAll());
//    }
//
//    @PostMapping("/update-info")
//    public ResponseEntity<Long> updateOrderInfo(@RequestBody Order order){
//        return ResponseEntity.ok(orderService.update(order));
//    }
//
//    @PostMapping("/update-status")
//    public ResponseEntity<Long> updateOrderStatus(@RequestBody Order order){
//        return ResponseEntity.ok(orderService.updateOrderStatus(order));
//    }
//
//    @PostMapping("/update-paid")
//    public ResponseEntity<Long> updateOrderPaid(@RequestBody Order order){
//        return ResponseEntity.ok(orderService.updateOrderSetPaid(order));
//    }
//
//    @Secured(value = "ROLE_ADMIN")
//    @DeleteMapping("/remove")
//    public ResponseEntity<Long> removeOrder(@RequestBody Order order) {
//        return ResponseEntity.ok(orderService.delete(order));
//    }
//
//    @PutMapping("/save")
//    public ResponseEntity<Long> saveOrder(@RequestBody Order order){
//        return ResponseEntity.ok(orderService.save(order));
//    }
}
