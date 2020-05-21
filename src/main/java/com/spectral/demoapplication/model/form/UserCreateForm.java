package com.spectral.demoapplication.model.form;

import com.spectral.demoapplication.validation.annotation.CountryExistConstraint;
import com.spectral.demoapplication.validation.annotation.EmailExistConstraint;
import com.spectral.demoapplication.validation.annotation.RoleExistConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class UserCreateForm {
    @NotBlank(message = "Name is required.")
    @Length(min = 1, max = 32, message = "Name must be between 1 and 32 characters")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u00FF \\-]*",
            message = "Small or capital Latin letters only. Split with a space or a hyphen.")
    private String name;

    @Email(message = "Illegal e-mail format.")
    @NotBlank(message = "E-mail is required.")
    @EmailExistConstraint
    private String email;

    @NotBlank(message = "Country is required.")
    private String countryName;

    @NotNull(message = "Roles is required.")
    private List<String> roleNames;
}
