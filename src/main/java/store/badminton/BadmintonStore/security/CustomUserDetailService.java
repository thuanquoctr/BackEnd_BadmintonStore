package store.badminton.BadmintonStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.User;
import store.badminton.BadmintonStore.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database by username
        User user = this.userRepo.findUserByUsername(username);
        return user;
    }
}
