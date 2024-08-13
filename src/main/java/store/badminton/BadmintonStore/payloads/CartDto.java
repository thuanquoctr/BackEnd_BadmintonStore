package store.badminton.BadmintonStore.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import store.badminton.BadmintonStore.entities.CartItem;
import store.badminton.BadmintonStore.entities.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private long id;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

}