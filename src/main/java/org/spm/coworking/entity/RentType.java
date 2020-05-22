package org.spm.coworking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * A ClientSystem.
 */
@Data
@Entity
@Table(name = "rent_type")
public class RentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForRentType")
    @SequenceGenerator(name = "sequenceGeneratorForRentType")
    @Column(name = "rent_type_id", nullable = false, unique = true)
    private Long rentTypeId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "rentTypes")
    private Set<Office> offices;

}
