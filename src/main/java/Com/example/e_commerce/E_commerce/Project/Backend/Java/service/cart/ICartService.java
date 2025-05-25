package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.cart;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}
