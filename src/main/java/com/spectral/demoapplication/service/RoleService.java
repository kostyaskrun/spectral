package com.spectral.demoapplication.service;

import com.spectral.demoapplication.model.entity.Role;
import com.spectral.demoapplication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    @Transactional(readOnly = true)
    public Set<Role> getRolesByNames(List<String> roleNames) {
        Set<Role> result = new HashSet<>();
        for (String roleName : roleNames) {
            result.add(repository.findRoleByNameIgnoreCase(roleName).orElseThrow(() ->
                    new EmptyResultDataAccessException(
                            String.format("Service %s, could not found entity by role name: %s", this, roleName), 1)));
        }
        return result;
    }
}
