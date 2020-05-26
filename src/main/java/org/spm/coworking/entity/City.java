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
@Table(name = "city")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForCity")
    @SequenceGenerator(name = "sequenceGeneratorForCity")
    @Column(name = "city_id", nullable = false, unique = true)
    private Long cityId;

    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy="cityId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Office> offices;

    @Override
    public String toString() {
        return "City [cityId=" + cityId
                + ", name=" + name
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

        City city = (City) obj;
        return city.equals(city.cityId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
