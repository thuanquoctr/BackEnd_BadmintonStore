package store.badminton.BadmintonStore.payloads;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.badminton.BadmintonStore.entities.Product;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class CategoryDto {
    private long id;
    private String name;
    private Date createDate = new Date();
}
