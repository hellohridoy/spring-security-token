package Com.example.e_commerce.E_commerce.Project.Backend.Java.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
