package store.badminton.BadmintonStore.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Cart;
import store.badminton.BadmintonStore.entities.CartItem;
import store.badminton.BadmintonStore.entities.Product;
import store.badminton.BadmintonStore.payloads.CartDto;
import store.badminton.BadmintonStore.payloads.CartItemDto;
import store.badminton.BadmintonStore.repositories.CartItemRepo;
import store.badminton.BadmintonStore.repositories.CartRepo;
import store.badminton.BadmintonStore.repositories.ProductRepo;
import store.badminton.BadmintonStore.services.CartService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private ProductRepo productRepo;
    private CartRepo cartRepo;
    private CartItemRepo cartItemRepo;
    private ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(ProductRepo productRepo, CartRepo cartRepo, CartItemRepo cartItemRepo) {
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CartDto getCart(long user_id) {
        Cart cart = cartRepo.findByUserId(user_id);
        return this.CartToDto(cart);
    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        Cart cart = modelMapper.map(cartDto, Cart.class);
        cartRepo.save(cart);
        return this.CartToDto(cart);
    }


    @Override
    public CartDto addItem(long user_id, long product_id, int quantity) {
        CartDto cartDto = this.getCart(user_id);
        Product product = productRepo.findById(product_id).get();
        if (product != null && cartDto != null) {
            List<CartItem> cartItems = cartDto.getCartItems();
            CartItem cartItem = new CartItem();
            for (CartItem item : cartItems) {
                if (item.getProduct().getId() == product.getId()) {
                    item.setQuantity(item.getQuantity() + quantity);
                    cartItemRepo.save(item);
                    return cartDto;
                }
            }
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(this.DtoToCart(cartDto));
            cartItems.add(cartItem);
            cartDto.setCartItems(cartItems);
            this.saveCart(cartDto);
        }
        return cartDto;
    }

    @Override
    public CartDto removeItem(long user_id, long product_id) {
        CartDto cartDto = this.getCart(user_id);
        cartDto.getCartItems().removeIf((item) -> item.getProduct().getId() == product_id);
        return this.saveCart(cartDto);
    }

    @Override
    public Cart updateItem(int user_id, int product_id, int qty) {
        return null;
    }

    @Override
    public void clear(int user_id) {

    }

    @Override
    public Collection<CartItemDto> getItems(long user_id) {
        CartDto cartDto = this.getCart(user_id);
        List<CartItemDto> cartItems = cartDto.getCartItems().stream().map(item -> this.CartItemToDto(item)).collect(Collectors.toList());
        return cartItems;
    }

    @Override
    public int getCount(long user_id) {
        CartDto cartDto = this.getCart(user_id);
        int tong = 0;
        for (CartItem item : cartDto.getCartItems()) {
            tong += item.getQuantity();
        }
        return tong;
    }

    @Override
    public double getAmount(long user_id) {
        CartDto cartDto = this.getCart(user_id);
        if (cartDto.getCartItems().isEmpty()) {
            return 0;
        }
        return cartDto.getCartItems().stream().mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice()).sum();
    }

    @Override
    public void increaseQuantity(CartItem cartItem) {
        CartItem item = cartItemRepo.findById(cartItem.getId()).get();
        item.incrementQuantity();
        cartItemRepo.save(item);
    }

    @Override
    public void decreaseQuantity(int userId, int product_id) {

    }

    public CartDto CartToDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return cartDto;
    }

    public Cart DtoToCart(CartDto cartDto) {
        Cart cart = modelMapper.map(cartDto, Cart.class);
        return cart;
    }

    public CartItemDto CartItemToDto(CartItem cartItem) {
        CartItemDto cartItemDto = modelMapper.map(cartItem, CartItemDto.class);
        return cartItemDto;
    }

}
