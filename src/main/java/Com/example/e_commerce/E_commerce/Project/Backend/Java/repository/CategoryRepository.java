package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name);

  boolean existsByName(String name);
}
