package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);

    // Main query methods needed for the service

    // Method 1: Find cart item by cart ID and product ID (returns Optional)
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    Optional<CartItem> findByCart_IdAndProduct_Id(@Param("cartId") Long cartId, @Param("productId") Long productId);

    // Method 2: Find all cart items for a specific cart
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);

    // Method 3: Check if cart item exists by cart ID and product ID
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    boolean existsByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    // Method 4: Count items in a cart
    @Query("SELECT COUNT(ci) FROM CartItem ci WHERE ci.cart.id = :cartId")
    Long countByCartId(@Param("cartId") Long cartId);

    // Method 5: Get total quantity for a cart
    @Query("SELECT COALESCE(SUM(ci.quantity), 0) FROM CartItem ci WHERE ci.cart.id = :cartId")
    Integer getTotalQuantityByCartId(@Param("cartId") Long cartId);

    // Method 6: Delete cart item by cart ID and product ID
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    void deleteByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    // Alternative using Spring Data JPA naming convention (these should work automatically)

    // Alternative method 1: Using property navigation
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    // Alternative method 2: Using underscore notation
    List<CartItem> findByCart_Id(Long cartId);

    // Alternative method 3: Exists method
    boolean existsByCart_IdAndProduct_Id(Long cartId, Long productId);

    // Alternative method 4: Delete method
    @Modifying
    @Transactional
    void deleteByCart_IdAndProduct_Id(Long cartId, Long productId);

    // Additional useful methods

    // Find all cart items for a specific product across all carts
    List<CartItem> findByProductId(Long productId);

    // Find cart items with quantity greater than specified amount
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.quantity > :quantity")
    List<CartItem> findByCartIdAndQuantityGreaterThan(@Param("cartId") Long cartId, @Param("quantity") Integer quantity);

    // Find cart items by price range
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.unitPrice BETWEEN :minPrice AND :maxPrice")
    List<CartItem> findByCartIdAndPriceRange(@Param("cartId") Long cartId,
                                             @Param("minPrice") BigDecimal minPrice,
                                             @Param("maxPrice") BigDecimal maxPrice);

    // Get cart total amount by summing all item totals
    @Query("SELECT COALESCE(SUM(ci.totalPrice), 0) FROM CartItem ci WHERE ci.cart.id = :cartId")
    BigDecimal getCartTotalAmount(@Param("cartId") Long cartId);

    // Find cart items by product name (useful for search)
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND LOWER(ci.product.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<CartItem> findByCartIdAndProductNameContaining(@Param("cartId") Long cartId, @Param("productName") String productName);


    // Find the most expensive item in a cart
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId ORDER BY ci.unitPrice DESC")
    List<CartItem> findByCartIdOrderByUnitPriceDesc(@Param("cartId") Long cartId);

    // Find items with low stock (assuming you have inventory field in product)
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.inventory < :threshold")
    List<CartItem> findByCartIdAndLowStock(@Param("cartId") Long cartId, @Param("threshold") Integer threshold);
}
