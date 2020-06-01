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

/*    @Lob
    @Column(name = "icon", columnDefinition="BLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte[] icon;*/

    @OneToOne(mappedBy = "feature", cascade = CascadeType.MERGE)
    private Image featureIcon;

    @ManyToMany(mappedBy = "features", fetch = FetchType.EAGER)
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

        Feature city = (Feature) obj;
        return city.equals(city.featureId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

}
