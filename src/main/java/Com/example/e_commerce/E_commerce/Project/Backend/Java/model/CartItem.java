package Com.example.e_commerce.E_commerce.Project.Backend.Java.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(nullable = false)
    private Long version = 0L;  // Initialize with default value

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 38, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 38, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Custom constructor for new cart items
    public CartItem(Cart cart, Product product, Integer quantity, BigDecimal unitPrice) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.version = 0L;
        setTotalPrice();
    }

    public void setTotalPrice() {
        if (unitPrice != null && quantity != null) {
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    // Initialize version if null (for existing entities)
    @PrePersist
    @PreUpdate
    public void initializeVersion() {
        if (this.version == null) {
            this.version = 0L;
        }
    }
}
