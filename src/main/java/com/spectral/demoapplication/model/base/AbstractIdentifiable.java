package com.spectral.demoapplication.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Base class for all application entities
 * which have unique identifier
 */
@Getter
@Setter
@MappedSuperclass
@ToString(of = "id")
public abstract class AbstractIdentifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    protected Long id;
}
