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

    @Column(name = "count_of_hours")
    private Integer countOfHours;

    @ManyToMany(mappedBy = "durationTypes", fetch = FetchType.EAGER)
    @Fetch(value=FetchMode.SELECT)
    private Set<Office> offices;

    @OneToMany(mappedBy="durationTypeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "DurationType [durationTypeId=" + durationTypeId
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

        DurationType durationType = (DurationType) obj;
        return durationTypeId.equals(durationType.durationTypeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((durationTypeId == null) ? 0 : durationTypeId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

}
