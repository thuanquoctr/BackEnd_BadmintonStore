package store.badminton.BadmintonStore.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Category;
import store.badminton.BadmintonStore.entities.Product;
import store.badminton.BadmintonStore.payloads.CategoryDto;
import store.badminton.BadmintonStore.payloads.ProductDto;
import store.badminton.BadmintonStore.repositories.CategoryRepo;
import store.badminton.BadmintonStore.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category = categoryRepo.save(category);
        return categorytoCategoryDto(category);
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        return categorytoCategoryDto(categoryRepo.getOne(id));
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category categoryToUpdate = categoryRepo.getOne(categoryDto.getId());
        categoryToUpdate.setName(categoryDto.getName());
        Category updatedCategory = categoryRepo.save(categoryToUpdate);
        return categorytoCategoryDto(updatedCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategorys() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.categorytoCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;

    }

    public CategoryDto categorytoCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
