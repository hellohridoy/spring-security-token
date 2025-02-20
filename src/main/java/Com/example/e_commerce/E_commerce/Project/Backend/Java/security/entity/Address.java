package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Table(name = "addresses")
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    private boolean isDefault;
}
