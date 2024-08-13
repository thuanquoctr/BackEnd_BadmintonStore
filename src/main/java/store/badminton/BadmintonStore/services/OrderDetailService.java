package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.payloads.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getOrderDetailByOderId(long id);
}
