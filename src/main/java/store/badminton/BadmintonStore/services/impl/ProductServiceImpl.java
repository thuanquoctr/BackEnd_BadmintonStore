package store.badminton.BadmintonStore.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Category;
import store.badminton.BadmintonStore.entities.Product;
import store.badminton.BadmintonStore.payloads.ProductDto;
import store.badminton.BadmintonStore.repositories.CategoryRepo;
import store.badminton.BadmintonStore.repositories.ProductRepo;
import store.badminton.BadmintonStore.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.modelMapper = new ModelMapper();
        this.categoryRepo = categoryRepo;

    }

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto, Long category_id) {
        Category category = categoryRepo.findById(category_id).orElse(null);
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        Product productNew = productRepo.save(product);
        return modelMapper.map(productNew, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepo.findById(id).orElse(null);
        return productToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = dtoToProduct(productDto);
        Product findProduct = productRepo.findById(product.getId()).orElse(null);
        if (findProduct != null) {
            Product updatedProduct = productRepo.saveAndFlush(product);
            return productToDto(updatedProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> this.productToDto(product)).collect(Collectors.toList());
        return productDtos;
    }

    public Product dtoToProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        return product;
    }

    public ProductDto productToDto(Product product) {
        ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
        return productDto;
    }
}
