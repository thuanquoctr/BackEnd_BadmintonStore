package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.entities.Cart;
import store.badminton.BadmintonStore.entities.CartItem;
import store.badminton.BadmintonStore.payloads.CartDto;
import store.badminton.BadmintonStore.payloads.CartItemDto;

import java.util.Collection;

public interface CartService {
    public CartDto getCart(long user_id);

    public CartDto saveCart(CartDto cartDto);

    public CartDto addItem(long user_id, long product_id,int quantity);

    public CartDto removeItem(long user_id, long product_id);

    public Cart updateItem(int user_id, int product_id, int qty);

    public void clear(int user_id);

    public Collection<CartItemDto> getItems(long user_id);

    public int getCount(long user_id);

    public double getAmount(long user_id);

    public void increaseQuantity(CartItem cartItem);

    public void decreaseQuantity(int userId, int product_id);
}
