package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.category;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);

}
