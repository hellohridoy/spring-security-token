package Com.example.e_commerce.E_commerce.Project.Backend.Java.dto;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}
