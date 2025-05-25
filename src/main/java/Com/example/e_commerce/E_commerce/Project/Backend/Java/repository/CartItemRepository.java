package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
