package com.spectral.demoapplication.service;

import com.spectral.demoapplication.mapper.UserMapper;
import com.spectral.demoapplication.model.dto.UserDto;
import com.spectral.demoapplication.model.entity.User;
import com.spectral.demoapplication.model.form.UserCreateForm;
import com.spectral.demoapplication.model.form.UserUpdateForm;
import com.spectral.demoapplication.model.specification.UserSpecification;
import com.spectral.demoapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public void createUser(UserCreateForm form) {
        User user = mapper.mapToEntity(form);
        repository.save(user);
    }

    @Transactional
    public void updateUser(UserUpdateForm form) {
        User user = repository.findById(form.getUserId()).orElseThrow(() ->
                new EmptyResultDataAccessException(
                        String.format("Service %s, could not found entity by id: %d", this, form.getUserId()), 1));
        mapper.updateEntity(form, user);
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(final String email) {
        return repository.findUserByEmail(email);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsersByFilter(String name, String email, String countryName,
                                          String roleName, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return repository.findAll(UserSpecification.findUserBySearchParameters(name, email, countryName, roleName, dateFrom, dateTo))
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
