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
@Table(name = "metro")
public class Metro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForMetro")
    @SequenceGenerator(name = "sequenceGeneratorForMetro")
    @Column(name = "metro_id", nullable = false, unique = true)
    private Long metroId;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "metros")
    private Set<Office> offices;

}
