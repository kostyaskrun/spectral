package com.spectral.demoapplication.mapper;

import com.spectral.demoapplication.model.dto.UserDto;
import com.spectral.demoapplication.model.entity.User;
import com.spectral.demoapplication.model.form.UserCreateForm;
import com.spectral.demoapplication.model.form.UserUpdateForm;
import com.spectral.demoapplication.service.CountryService;
import com.spectral.demoapplication.service.RoleService;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {CountryService.class, RoleService.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    UserDto mapToDto(User entity);

    @Mappings({
            @Mapping(source = "countryName", target = "country"),
            @Mapping(source = "roleNames", target = "roles"),
    })
    User mapToEntity(UserCreateForm form);

    @Mappings({
            @Mapping(source = "countryName", target = "country"),
            @Mapping(source = "roleNames", target = "roles"),
    })
    void updateEntity(UserUpdateForm form, @MappingTarget User user);

}
