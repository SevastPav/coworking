package org.spm.coworking.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToMany(mappedBy = "metros", fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    private Set<Office> offices;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Metro metro = (Metro) obj;
        return metro.equals(metro.metroId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((metroId == null) ? 0 : metroId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
