package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.product;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.dto.ProductDto;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Product;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.request.AddProductRequest;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
