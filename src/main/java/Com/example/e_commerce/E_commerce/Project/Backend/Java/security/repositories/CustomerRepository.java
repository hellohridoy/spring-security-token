package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.repositories;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsernameOrEmail(String username, String email);

}
