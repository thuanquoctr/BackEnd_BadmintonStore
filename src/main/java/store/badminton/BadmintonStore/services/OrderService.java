package store.badminton.BadmintonStore.services;


import store.badminton.BadmintonStore.entities.Order;
import store.badminton.BadmintonStore.payloads.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();

    List<OrderDto> getOrderByUserId(long id);

    OrderDto createOrder(OrderDto order);

    OrderDto getByID(long id);

    OrderDto changeStatus(long order_id, long status);
}
