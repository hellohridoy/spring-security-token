package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.service;

import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {

    void uploadProfilePicture(Long userId, MultipartFile file);
}
