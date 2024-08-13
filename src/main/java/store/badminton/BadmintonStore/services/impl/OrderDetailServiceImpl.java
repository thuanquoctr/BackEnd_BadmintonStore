package store.badminton.BadmintonStore.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.OrderDetail;
import store.badminton.BadmintonStore.payloads.OrderDetailDto;
import store.badminton.BadmintonStore.repositories.OrderDetailRepo;
import store.badminton.BadmintonStore.services.OrderDetailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepo orderDetailRepo;
    private ModelMapper modelMapper;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepo orderDetailRepo) {
        this.orderDetailRepo = orderDetailRepo;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<OrderDetailDto> getOrderDetailByOderId(long id) {
        List<OrderDetail> orderDetails = orderDetailRepo.getOrderDetailByOder_Id(id);
            List<OrderDetailDto> orderDetailDtos = orderDetails.stream().map(item -> this.toDTO(item)).collect(Collectors.toList());
        return orderDetailDtos;
    }
    private OrderDetailDto toDTO(OrderDetail orderDetail) {
        return this.modelMapper.map(orderDetail, OrderDetailDto.class);
    }
}
