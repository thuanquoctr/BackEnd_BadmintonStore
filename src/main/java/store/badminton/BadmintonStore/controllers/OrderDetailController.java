package store.badminton.BadmintonStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.badminton.BadmintonStore.payloads.OrderDetailDto;
import store.badminton.BadmintonStore.services.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {
    private OrderDetailService orderDetailService;
    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetailByOrderId(@PathVariable long id) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailByOderId(id));
    }
}
