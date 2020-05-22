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
@Table(name = "duration_type")
public class DurationType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForDurationType")
    @SequenceGenerator(name = "sequenceGeneratorForDurationType")
    @Column(name = "duration_type_id", nullable = false, unique = true)
    private Long durationTypeId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "durationTypes")
    private Set<Office> offices;

}
