package store.badminton.BadmintonStore.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import store.badminton.BadmintonStore.payloads.ProductDto;
import store.badminton.BadmintonStore.services.FileService;
import store.badminton.BadmintonStore.services.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    private FileService fileService;

    @Autowired
    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @Value("${project.image}")
    private String path;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/{idCategory}/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable long idCategory) {
        ProductDto productResult = productService.createProduct(productDto, idCategory);
        return new ResponseEntity<>(productResult, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto productResult = productService.updateProduct(productDto);
        return ResponseEntity.ok(productResult);
    }

    //    @DeleteMapping("/{id}")
//    public ResponseEntity<ProductDto> deleteProduct(@PathVariable long id) {
//        productService.deleteProduct(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @PostMapping("/image/upload/{productId}")
    public ResponseEntity<ProductDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                      @PathVariable Integer productId) throws IOException {
        ProductDto productDto = this.productService.getProductById(productId);
        String fileName = this.fileService.uploadImage(path, image);
        productDto.setPictureMain(fileName);
        ProductDto updateProduct = this.productService.updateProduct(productDto);
        return new ResponseEntity<ProductDto>(updateProduct, HttpStatus.OK);

    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
