package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.SSecurity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity.Customer;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        return customer; // Return the Customer entity (implements UserDetails)
    }
}
