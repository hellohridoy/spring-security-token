package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.cart;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Cart;
import java.math.BigDecimal;

public interface ICartService {
    Long initializeNewCart();
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);

    // Add these new methods for user-based cart operations
    Cart getCartByUserId(Long userId);
    Long getOrCreateCartForUser(Long userId);
    void assignCartToUser(Long cartId, Long userId);
}
