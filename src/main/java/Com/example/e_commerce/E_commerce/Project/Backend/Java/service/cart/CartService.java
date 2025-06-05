package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.cart;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.exceptions.ResourceNotFoundException;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Cart;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.User;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.repository.CartRepository;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long initializeNewCart() {
        try {
            Cart newCart = new Cart(BigDecimal.ZERO);
            log.debug("Creating new cart with version: {}", newCart.getVersion());

            Cart savedCart = cartRepository.save(newCart);
            log.debug("Saved cart with ID: {} and version: {}", savedCart.getId(), savedCart.getVersion());

            return savedCart.getId();
        } catch (Exception e) {
            log.error("Failed to initialize new cart", e);
            throw new RuntimeException("Failed to initialize new cart: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        if (cart.getVersion() == null) {
            log.warn("Cart {} has null version, this should not happen", cartId);
        }

        return cart;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCartByUserId(Long userId) {
        log.debug("Getting cart for user ID: {}", userId);

        // First check if user exists
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Find cart by user ID
        Optional<Cart> cartOpt = Optional.ofNullable(cartRepository.findByUserId(userId));

        if (cartOpt.isPresent()) {
            log.debug("Found existing cart for user {}: {}", userId, cartOpt.get().getId());
            return cartOpt.get();
        } else {
            log.debug("No cart found for user {}, creating new cart", userId);
            // Create new cart for user if none exists
            Cart newCart = new Cart(BigDecimal.ZERO);
            newCart.setUser(user);
            Cart savedCart = cartRepository.save(newCart);
            log.debug("Created new cart {} for user {}", savedCart.getId(), userId);
            return savedCart;
        }
    }

    @Override
    @Transactional
    public Long getOrCreateCartForUser(Long userId) {
        Cart cart = getCartByUserId(userId);
        return cart.getId();
    }

    @Override
    @Transactional
    public void assignCartToUser(Long cartId, Long userId) {
        log.debug("Assigning cart {} to user {}", cartId, userId);

        Cart cart = getCart(cartId);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        cart.setUser(user);
        cartRepository.save(cart);
        log.debug("Successfully assigned cart {} to user {}", cartId, userId);
    }

    @Override
    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = getCart(cartId);
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getTotalAmount();
    }
}
