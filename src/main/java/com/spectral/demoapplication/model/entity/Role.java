package com.spectral.demoapplication.model.entity;

import com.spectral.demoapplication.model.base.AbstractVersionalIdentifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractVersionalIdentifiable {

    @Column(name = "name", nullable = false)
    private String name;
}
