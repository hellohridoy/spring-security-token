package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.SSecurity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity.Customer;

public class AuthResponse {
    private String token;
    private Customer customer;

    public AuthResponse(String token, Customer customer) {
        this.token = token;
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
// Add constructor, getters and setters
}
