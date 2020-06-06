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
@Table(name = "place")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForPlace")
    @SequenceGenerator(name = "sequenceGeneratorForPlace")
    @Column(name = "place_id", nullable = false, unique = true)
    private Long placeId;

    @Column(name = "internal_place_id", nullable = false)
    private Long internalPlaceId;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="office_id")
    private Office officeId;

    @OneToMany(mappedBy="placeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Place place = (Place) obj;
        return place.equals(place.placeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
        result = prime * result + ((internalPlaceId == null) ? 0 : internalPlaceId.hashCode());
        return result;
    }

}
