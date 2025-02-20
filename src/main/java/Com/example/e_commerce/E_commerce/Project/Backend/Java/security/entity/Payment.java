package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.PaymentMethod;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // CREDIT_CARD, DEBIT_CARD, PAYPAL, COD

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(unique = true)
    private String transactionId;

    private LocalDateTime paymentDate = LocalDateTime.now();
}
