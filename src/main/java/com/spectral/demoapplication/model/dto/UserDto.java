package com.spectral.demoapplication.model.dto;

import com.spectral.demoapplication.model.entity.Country;
import com.spectral.demoapplication.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private Country country;
    private Set<Role> roles;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
