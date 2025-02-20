package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.controllers;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.repositories.CustomerRepository;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping("/api/v1/customers/customers-infos/profile-picture")
    public void uploadProfilePicture(Long userId, MultipartFile file) {
    customerRepository.findById(userId).ifPresent(customer -> {
        try {
            customer.setProfilePicture(file.getBytes());  // Convert file to byte array
            customer.setFileType(file.getContentType());  // Set file type (e.g., image/png)
            customerService.uploadProfilePicture(userId,file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }
    });

}
    }


