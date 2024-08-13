package store.badminton.BadmintonStore.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.badminton.BadmintonStore.entities.*;

import java.util.*;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Set<Role> roles;
}
