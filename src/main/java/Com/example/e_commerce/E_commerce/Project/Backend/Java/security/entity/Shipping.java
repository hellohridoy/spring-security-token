package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus = ShippingStatus.PENDING;

    private LocalDateTime estimatedDelivery;
    private LocalDateTime actualDelivery;
}
