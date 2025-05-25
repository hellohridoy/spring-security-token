package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
