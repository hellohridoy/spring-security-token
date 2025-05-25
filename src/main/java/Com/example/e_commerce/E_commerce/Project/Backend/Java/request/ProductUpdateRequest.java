package Com.example.e_commerce.E_commerce.Project.Backend.Java.request;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
