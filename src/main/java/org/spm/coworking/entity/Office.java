package org.spm.coworking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * A ClientSystem.
 */
@Data
@Entity
@Table(name = "office")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForOffice")
    @SequenceGenerator(name = "sequenceGeneratorForOffice")
    @Column(name = "office_id", nullable = false, unique = true)
    private Long officeId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @OneToMany(mappedBy="officeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Place> places;

    @ManyToMany
    @JoinTable(name = "office_features",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private Set<Feature> features;

}
