package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.image;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.dto.ImageDto;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file,  Long imageId);
}
