package store.badminton.BadmintonStore.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import store.badminton.BadmintonStore.entities.Cart;
import store.badminton.BadmintonStore.entities.Product;
@Data
public class CartItemDto {
    private long id;
    private ProductDto product;
    private int quantity;
    @JsonIgnore
    private CartDto cart;
}
