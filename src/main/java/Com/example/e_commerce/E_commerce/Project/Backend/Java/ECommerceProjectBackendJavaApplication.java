package Com.example.e_commerce.E_commerce.Project.Backend.Java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity")
public class ECommerceProjectBackendJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceProjectBackendJavaApplication.class, args);
	}

}
