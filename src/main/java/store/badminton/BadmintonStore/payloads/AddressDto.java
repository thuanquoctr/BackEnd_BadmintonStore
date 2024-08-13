package store.badminton.BadmintonStore.payloads;

import lombok.Data;
import store.badminton.BadmintonStore.entities.User;

@Data
public class AddressDto {
    private long id;
    private String name;
    private String street;
    private String city;
    private String ward;
    private String district;
    private String phone;
    private UserDto user;
}
