package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.user;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.dto.UserDto;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.User;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.request.CreateUserRequest;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
