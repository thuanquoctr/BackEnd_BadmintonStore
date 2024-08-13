package store.badminton.BadmintonStore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.badminton.BadmintonStore.entities.Order;
import store.badminton.BadmintonStore.entities.OrderStatus;
import store.badminton.BadmintonStore.payloads.OrderDto;
import store.badminton.BadmintonStore.services.OrderService;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getByID(id));
    }

    @GetMapping("/changeStatus/{orderId}")
    public ResponseEntity<OrderDto> changeStatus(@PathVariable long orderId, @RequestParam long status) {
        return ResponseEntity.ok(orderService.changeStatus(orderId,status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderByUserId(id));
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

}
