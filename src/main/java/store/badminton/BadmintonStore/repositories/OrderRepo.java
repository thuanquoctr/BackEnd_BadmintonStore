package store.badminton.BadmintonStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.badminton.BadmintonStore.entities.Order;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getAllByUserId(long id);

    Order getOrderById(long id);
}
