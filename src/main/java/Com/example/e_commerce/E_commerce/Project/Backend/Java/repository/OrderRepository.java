package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long userId);
}
