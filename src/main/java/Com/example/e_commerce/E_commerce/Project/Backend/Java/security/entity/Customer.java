package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.Gender;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @Lob
    private byte[] profilePicture;

    private String fileType;

    private Gender gender;

    private LocalDateTime dateOfBirth;

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's role as a GrantedAuthority
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Use email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return isEmailVerified; // Account is enabled only if email is verified
    }

}




