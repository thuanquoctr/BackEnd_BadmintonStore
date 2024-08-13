package store.badminton.BadmintonStore.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Order;
import store.badminton.BadmintonStore.entities.OrderDetail;
import store.badminton.BadmintonStore.entities.OrderStatus;
import store.badminton.BadmintonStore.entities.Product;
import store.badminton.BadmintonStore.payloads.OrderDetailDto;
import store.badminton.BadmintonStore.payloads.OrderDto;
import store.badminton.BadmintonStore.payloads.UserDto;
import store.badminton.BadmintonStore.repositories.OrderRepo;
import store.badminton.BadmintonStore.repositories.ProductRepo;
import store.badminton.BadmintonStore.repositories.UserRepo;
import store.badminton.BadmintonStore.services.OrderService;
import store.badminton.BadmintonStore.services.UserService;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;
    private ProductRepo productRepo;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo,ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepo.findAll();
        List<OrderDto> orderDtos = orders.stream().map(oder -> this.toDto(oder)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrderByUserId(long id) {
        List<Order> orders = orderRepo.getAllByUserId(id);
        List<OrderDto> orderDtos = orders.stream().map(oder -> this.toDto(oder)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto createOrder(OrderDto order) {
        Order ordernew = this.toOrder(order);
        ordernew.setCode_oder(UUID.randomUUID().toString());
        ordernew.setCreateDate(new Date());
        List<OrderDetailDto> orderDetailDtos = order.getOrderDetails();
        ordernew.setOrderDetails(convertToOrderDetails(orderDetailDtos, ordernew));
        return this.toDto(orderRepo.save(ordernew));
    }

    @Override
    public OrderDto getByID(long id) {
        Order order = orderRepo.getOrderById(id);
        return toDto(order);
    }

    @Override
    public OrderDto changeStatus(long order_id, long status) {
        Order order = orderRepo.getById(order_id);
        if (order != null) {
            if (status == 1) {
                order.setStatus(OrderStatus.CHUA_XAC_NHAN);
            } else if (status == 2) {
                order.setStatus(OrderStatus.DA_XAC_NHAN);
            } else if (status == 3) {
                order.setStatus(OrderStatus.DANG_GIAO_HANG);
            } else if (status == 4) {
                order.setStatus(OrderStatus.DA_GIAO_HANG);
            } else if (status == 5) {
                order.setStatus(OrderStatus.DA_HUY);
            }
        }
        return toDto(orderRepo.save(order));
    }


    private List<OrderDetail> convertToOrderDetails(List<OrderDetailDto> orderDetailDtos, Order order) {
        return orderDetailDtos.stream()
                .map(dto -> {
                    OrderDetail orderDetail = new OrderDetail();
                    Product product = productRepo.getById(dto.getProduct().getId());
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(dto.getQuantity());
                    orderDetail.setOder(order);
                    return orderDetail;
                })
                .collect(Collectors.toList());
    }
    private OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    private Order toOrder(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
