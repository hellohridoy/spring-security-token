package Com.example.e_commerce.E_commerce.Project.Backend.Java.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
