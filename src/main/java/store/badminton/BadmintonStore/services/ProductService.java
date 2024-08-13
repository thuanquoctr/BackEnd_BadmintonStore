package store.badminton.BadmintonStore.services;


import store.badminton.BadmintonStore.payloads.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, Long category_id);

    ProductDto getProductById(long id);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(long id);

    List<ProductDto> getAllProducts();
}
