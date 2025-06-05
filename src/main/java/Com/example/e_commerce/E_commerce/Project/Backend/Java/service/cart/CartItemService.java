package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.cart;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.exceptions.ResourceNotFoundException;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Cart;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.CartItem;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Product;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.repository.CartItemRepository;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.repository.CartRepository;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;

    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        log.debug("Adding item to cart. CartId: {}, ProductId: {}, Quantity: {}", cartId, productId, quantity);

        // Fetch fresh entities within transaction
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        Product product = productService.getProductById(productId);

        // Check if item already exists
        Optional<CartItem> existingItemOpt = cartItemRepository.findByCart_IdAndProduct_Id(cartId, productId);

        if (existingItemOpt.isPresent()) {
            // Update existing item
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice();
            cartItemRepository.save(existingItem);
            log.debug("Updated existing cart item with ID: {}", existingItem.getId());
        } else {
            // Create new item using constructor
            CartItem newItem = new CartItem(cart, product, quantity, product.getPrice());
            cartItemRepository.save(newItem);
            log.debug("Created new cart item with ID: {}", newItem.getId());
        }

        // Recalculate cart total
        updateCartTotal(cartId);
    }

    @Override
    @Transactional
    public void removeItemFromCart(Long cartId, Long itemId) {
        CartItem itemToRemove = cartItemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        if (!itemToRemove.getCart().getId().equals(cartId)) {
            throw new ResourceNotFoundException("Cart item does not belong to this cart");
        }

        cartItemRepository.delete(itemToRemove);
        updateCartTotal(cartId);
    }

    @Override
    @Transactional
    public void updateItemQuantity(Long cartId, Long itemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        if (!cartItem.getCart().getId().equals(cartId)) {
            throw new ResourceNotFoundException("Cart item does not belong to this cart");
        }

        cartItem.setQuantity(quantity);
        cartItem.setUnitPrice(cartItem.getProduct().getPrice());
        cartItem.setTotalPrice();
        cartItemRepository.save(cartItem);

        updateCartTotal(cartId);
    }

    @Override
    @Transactional(readOnly = true)
    public CartItem getCartItem(Long cartId, Long productId) {
        return cartItemRepository.findByCart_IdAndProduct_Id(cartId, productId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
    }

    private void updateCartTotal(Long cartId) {
        BigDecimal totalAmount = cartItemRepository.getCartTotalAmount(cartId);
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }
}
