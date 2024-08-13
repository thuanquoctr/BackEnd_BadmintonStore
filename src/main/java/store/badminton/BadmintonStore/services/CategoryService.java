package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.entities.Category;
import store.badminton.BadmintonStore.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(long id);

    List<CategoryDto> getAllCategorys();
}
