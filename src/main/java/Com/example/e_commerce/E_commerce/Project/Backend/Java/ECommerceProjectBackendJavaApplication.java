package Com.example.e_commerce.E_commerce.Project.Backend.Java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("Com.example.e_commerce.E_commerce.Project.Backend.Java.model") // Adjust to your entity package
@EnableJpaRepositories("Com.example.e_commerce.E_commerce.Project.Backend.Java.repository") // Adjust to your repo package
public class ECommerceProjectBackendJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceProjectBackendJavaApplication.class, args);
    }
}
