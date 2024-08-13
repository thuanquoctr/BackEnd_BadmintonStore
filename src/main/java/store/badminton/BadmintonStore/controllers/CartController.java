package store.badminton.BadmintonStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import store.badminton.BadmintonStore.entities.CartItem;
import store.badminton.BadmintonStore.payloads.CartDto;
import store.badminton.BadmintonStore.payloads.CartItemDto;
import store.badminton.BadmintonStore.services.CartService;
import store.badminton.BadmintonStore.services.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private CartService cartService;
    private UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/addCart/{user_id}/{product_id}/{quantity}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CartDto> getCart(@PathVariable long user_id, @PathVariable long product_id, @PathVariable int quantity) {
        CartDto cartDto = cartService.addItem(user_id, product_id, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }

    @GetMapping("/getItemByUser/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Collection<CartItemDto>> getAllCart(@PathVariable long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Collection<CartItemDto> cartItems = new ArrayList<>(cartService.getItems(id));
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/getCountByUserId/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Integer> getCountByUserId(@PathVariable long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cartService.getCount(id));
    }

    @DeleteMapping("/removeCartItemByUserId/{userId}/{productId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CartDto> removeCartItem(@PathVariable long userId, @PathVariable long productId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cartService.removeItem(userId, productId));
    }

    @PutMapping("/handleIncrementById/{user_id}/{product_id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CartDto> handleIncrementById(@PathVariable long user_id, @PathVariable long product_id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != user_id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CartDto cartDto = cartService.getCart(user_id);
        List<CartItem> cartItems = cartDto.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == product_id) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
        }
        cartDto.setCartItems(cartItems);
        cartService.saveCart(cartDto);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/handleDecrementById/{user_id}/{product_id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CartDto> handleDecrementById(@PathVariable long user_id, @PathVariable long product_id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != user_id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CartDto cartDto = cartService.getCart(user_id);
        List<CartItem> cartItems = cartDto.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == product_id) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                } else {
                    return ResponseEntity.ok(cartService.removeItem(user_id, product_id));
                }
            }
        }
        cartDto.setCartItems(cartItems);
        cartService.saveCart(cartDto);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/getAmountByUserId/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Double> getAmountByUserId(@PathVariable long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentUserId = userService.getUserByUsername(userDetails.getUsername()).getId();
        if (currentUserId != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cartService.getAmount(id));
    }


}
