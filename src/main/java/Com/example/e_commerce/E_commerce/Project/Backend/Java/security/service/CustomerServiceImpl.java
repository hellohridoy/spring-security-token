package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.service;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void uploadProfilePicture(Long userId, MultipartFile file) {
        customerRepository.findById(userId).ifPresent(customer -> {
            try {
                customer.setProfilePicture(file.getBytes());  // Convert file to byte array
                customer.setFileType(file.getContentType());  // Set file type (e.g., image/png)
                customerRepository.save(customer);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload profile picture", e);
            }
        });
    }
}
