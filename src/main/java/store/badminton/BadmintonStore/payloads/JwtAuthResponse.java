package store.badminton.BadmintonStore.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse {
    private String token;
}
