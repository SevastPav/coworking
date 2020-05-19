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
@Table(name = "feature")
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForFeature")
    @SequenceGenerator(name = "sequenceGeneratorForFeature")
    @Column(name = "feature_id", nullable = false, unique = true)
    private Long featureId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "features")
    private Set<Office> offices;

}
