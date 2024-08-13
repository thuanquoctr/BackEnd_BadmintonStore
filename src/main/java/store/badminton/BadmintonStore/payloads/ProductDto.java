package store.badminton.BadmintonStore.payloads;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.badminton.BadmintonStore.entities.Category;
import store.badminton.BadmintonStore.entities.Picture;
import store.badminton.BadmintonStore.entities.ProductReview;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String title;
    private String pictureMain;
    private String brand;
    private String description;
    private Double price;
    private Integer quantity;
    private int view = 0;
    private int quantitySold = 0;
    private CategoryDto category;
}
