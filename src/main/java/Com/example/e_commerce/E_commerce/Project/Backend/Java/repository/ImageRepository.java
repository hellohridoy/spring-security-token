package Com.example.e_commerce.E_commerce.Project.Backend.Java.repository;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
