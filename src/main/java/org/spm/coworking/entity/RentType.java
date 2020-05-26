package org.spm.coworking.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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

    @ManyToMany(mappedBy = "rentTypes", fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    private Set<Office> offices;

    @OneToMany(mappedBy="rentTypeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "RentType [rentTypeId=" + rentTypeId
                + ", title=" + title
                + ", description=" + description
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        RentType rentType = (RentType) obj;
        return rentType.equals(rentType.rentTypeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rentTypeId == null) ? 0 : rentTypeId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

}
