package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.SSecurity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity.Customer;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.enums.Role;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(CustomerRepository customerRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponses> registerCustomer(@RequestBody CustomerRegistrationDto registrationDto) {
        // Check if username/email already exists
        if (customerRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (customerRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email is already registered!");
        }

        // Create new customer
        Customer customer = new Customer();
        customer.setFirstName(registrationDto.getFirstName());
        customer.setLastName(registrationDto.getLastName());
        customer.setEmail(registrationDto.getEmail());
        customer.setUsername(registrationDto.getUsername());
        customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        customer.setRole(Role.USER);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setIsEmailVerified(false);


        // Save customer
        Customer savedCustomer = customerRepository.save(customer);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(savedCustomer);

        // Create response
        AuthResponses response = new AuthResponses(token, savedCustomer);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponses> loginCustomer(@RequestBody LoginRequest loginRequest) {
        try {
            // Find customer by username or email
            Customer customer = customerRepository.findByUsernameOrEmail(
                loginRequest.getUsername(),
                loginRequest.getUsername()
            ).orElseThrow(() -> new RuntimeException("User not found"));

            // Verify password
            if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            // Update last login
            customer.setLastLogin(LocalDateTime.now());
            customerRepository.save(customer);

            // Generate token
            String token = jwtTokenProvider.generateToken(customer);

            // Create response
            AuthResponses response = new AuthResponses(token, customer);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Invalid username/password combination");
        }
    }
}

// DTO classes
@Data
class CustomerRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}

@Data
class LoginRequest {
    private String username; // Can be username or email
    private String password;
}

@Data
@AllArgsConstructor
class AuthResponses {
    private String token;
    private Customer customer;
}
@Data
@AllArgsConstructor
class MessageResponse {
    private String message;
}
