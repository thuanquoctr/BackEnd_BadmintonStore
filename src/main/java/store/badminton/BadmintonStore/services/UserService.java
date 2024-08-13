package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.entities.User;
import store.badminton.BadmintonStore.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    UserDto updateUser(UserDto userDto);

    void deleteUser(long id);
    List<UserDto> getAllUsers();
    boolean checkIfUserExists(String username);
    boolean checkIfEmailExists(String email);
    boolean checkIfPhoneExists(String phone);
}
