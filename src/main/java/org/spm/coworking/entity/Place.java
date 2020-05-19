package org.spm.coworking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * A ClientSystem.
 */
@Data
@Entity
@Table(name = "place")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForPlace")
    @SequenceGenerator(name = "sequenceGeneratorForPlace")
    @Column(name = "place_id", nullable = false, unique = true)
    private Long placeId;

    @Column(name = "internal_place_id", nullable = false, unique = true)
    private Long internalPlaceId;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="office_id")
    private Office officeId;

    @OneToMany(mappedBy="placeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;

}
