package com.spectral.demoapplication.model.form;

import com.spectral.demoapplication.validation.annotation.UserUpdateFormConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@UserUpdateFormConstraint
@Getter
@Setter
public class UserUpdateForm {
    @NotNull(message = "User Id is required.")
    @Min(value = 0L, message = "Select user for update.")
    private Long userId;

    @NotBlank(message = "Name is required.")
    @Length(min = 1, max = 32, message = "Name must be between 1 and 32 characters.")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u00FF \\-]*",
            message = "Small or capital Latin letters only. Split with a space or a hyphen.")
    private String name;

    @Email(message = "Illegal e-mail format.")
    @NotBlank(message = "E-mail is required.")
    private String email;

    @NotBlank(message = "Country is required.")
    private String countryName;

    @NotNull(message = "Roles is required.")
    private List<String> roleNames;
}
