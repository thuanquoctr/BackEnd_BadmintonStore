package store.badminton.BadmintonStore.payloads;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.badminton.BadmintonStore.entities.Order;
import store.badminton.BadmintonStore.entities.Product;
@Data
@NoArgsConstructor
public class OrderDetailDto {
    private int id;
    private ProductDto product;
    private int quantity;
}
