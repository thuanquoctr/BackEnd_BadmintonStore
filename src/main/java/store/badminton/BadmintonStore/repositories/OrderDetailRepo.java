package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> getOrderDetailByOder_Id(long orderId);
}
