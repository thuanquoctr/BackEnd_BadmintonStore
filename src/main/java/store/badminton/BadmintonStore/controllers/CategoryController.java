package store.badminton.BadmintonStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.badminton.BadmintonStore.entities.Category;
import store.badminton.BadmintonStore.payloads.CategoryDto;
import store.badminton.BadmintonStore.payloads.UserDto;
import store.badminton.BadmintonStore.services.CategoryService;
import store.badminton.BadmintonStore.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable long id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategorys() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategorys();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto cr = categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>(cr, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
