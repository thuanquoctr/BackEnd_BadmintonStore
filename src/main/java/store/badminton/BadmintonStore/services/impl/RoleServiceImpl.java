package store.badminton.BadmintonStore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Role;
import store.badminton.BadmintonStore.repositories.RoleRepo;
import store.badminton.BadmintonStore.services.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findByName(name);
    }
}
