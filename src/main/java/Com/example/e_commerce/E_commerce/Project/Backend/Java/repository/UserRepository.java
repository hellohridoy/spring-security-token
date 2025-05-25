package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
