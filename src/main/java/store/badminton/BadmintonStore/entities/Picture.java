package store.badminton.BadmintonStore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pictureUrl;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST}
    )
    @JoinColumn(name = "product_id")
    private Product product;
}
