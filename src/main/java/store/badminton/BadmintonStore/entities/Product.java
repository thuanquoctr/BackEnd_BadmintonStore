package store.badminton.BadmintonStore.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String pictureMain;
    private String brand;
    @Column(length = 1000000000)
    private String description;
    private Double price;
    private Integer quantity;
    private int view = 0;
    private int quantitySold = 0;
    @ManyToOne(fetch = FetchType.LAZY, cascade =
            CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductReview> productReviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Picture> pictureList = new ArrayList<>();
}
