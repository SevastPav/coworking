package org.spm.coworking.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "price_per_hour")
    private Integer pricePerHour;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @OneToOne(mappedBy = "officeMapId",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    private Image officeMapImage;

    @OneToOne(mappedBy = "officeIconId",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    private Image officeMainImage;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="officePhotosId",
            cascade = CascadeType.MERGE)
    private List<Image> officeImages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserProfile adminId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id")
    private City cityId;

    @OneToMany(mappedBy="officeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Place> places;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "office_features",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    @Fetch(value=FetchMode.SELECT)
    private Set<Feature> features;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "office_rent_types",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "rent_type_id")})
    @Fetch(value= FetchMode.SELECT)
    private Set<RentType> rentTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "office_duration_types",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "duration_type_id")})
    @Fetch(value=FetchMode.SELECT)
    private Set<DurationType> durationTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "office_metros",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = {@JoinColumn(name = "metro_id")})
    @Fetch(value=FetchMode.SELECT)
    private Set<Metro> metros;

    @Override
    public String toString() {
        return "Office [office=" + officeId
                + ", title=" + title
                + ", address=" + address
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

        Office office = (Office) obj;
        return office.equals(office.officeId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((officeId == null) ? 0 : officeId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

}
