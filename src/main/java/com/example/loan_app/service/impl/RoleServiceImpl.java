package com.example.loan_app.service.impl;

import com.example.loan_app.entity.Role;
import com.example.loan_app.repository.RoleRepository;
import com.example.loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSaveRole(Role role) {
        Optional<Role> findRole = roleRepository.findByRole(role.getRole());
        return findRole.orElseGet(() -> roleRepository.save(role));
    }
}
