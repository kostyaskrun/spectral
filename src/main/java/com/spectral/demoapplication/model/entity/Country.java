package com.spectral.demoapplication.model.entity;

import com.spectral.demoapplication.model.base.AbstractVersionalIdentifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country extends AbstractVersionalIdentifiable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_code", nullable = false)
    private String shortCode;
}
