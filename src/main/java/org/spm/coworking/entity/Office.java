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

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserProfile adminId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id")
    private City cityId;

    @OneToMany(mappedBy="officeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Place> places;

    @ManyToMany
    @JoinTable(name = "office_features",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private Set<Feature> features;

    @ManyToMany
    @JoinTable(name = "office_rent_types",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "rent_type_id")})
    private Set<RentType> rentTypes;

    @ManyToMany
    @JoinTable(name = "office_duration_types",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "duration_type_id")})
    private Set<DurationType> durationTypes;

    @ManyToMany
    @JoinTable(name = "office_metros",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "metro_id")})
    private Set<Metro> metros;

}
