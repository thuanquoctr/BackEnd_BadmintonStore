package store.badminton.BadmintonStore.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;
import store.badminton.BadmintonStore.entities.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private long id;
    private String receiverNote;
    private String paymentMethods;
    private String shippingMethod;
    private double priceTotal;
    private String code_oder;
    private UserDto user;
    private Date createDate;
    private Date createTime;
    private OrderStatus status;
    private List<OrderDetailDto> orderDetails = new ArrayList<>();
}
