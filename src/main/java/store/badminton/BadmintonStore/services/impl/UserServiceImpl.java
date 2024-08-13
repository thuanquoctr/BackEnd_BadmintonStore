package store.badminton.BadmintonStore.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Cart;
import store.badminton.BadmintonStore.entities.Heart;
import store.badminton.BadmintonStore.entities.User;
import store.badminton.BadmintonStore.payloads.UserDto;
import store.badminton.BadmintonStore.repositories.UserRepo;
import store.badminton.BadmintonStore.services.RoleService;
import store.badminton.BadmintonStore.services.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        Cart cart = new Cart();
        Heart heart = new Heart();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByName("ROLE_USER")));
        user.setCart(cart);
        cart.setUser(user);
        user.setHeart(heart);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepo.findById(id).orElse(null);
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findUserByUsername(username);
        return this.userToDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User us = userRepo.getById(userDto.getId());
        return this.userToDto(userRepo.saveAndFlush(us));
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean checkIfPhoneExists(String phone) {
        return userRepo.existsByPhone(phone);
    }


    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
