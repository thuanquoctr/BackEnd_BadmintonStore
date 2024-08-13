package store.badminton.BadmintonStore.services;


import store.badminton.BadmintonStore.entities.Heart;
import store.badminton.BadmintonStore.payloads.UserDto;

import java.util.List;

public interface HeartService {
    Heart createHeart(UserDto userDto, Heart heart);

    void deleteHeart(long id);

    List<Heart> getAllHearts(UserDto userDto);
}
