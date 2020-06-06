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
@Table(name = "image")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForImage")
    @SequenceGenerator(name = "sequenceGeneratorForImage")
    @Column(name = "image_id", nullable = false, unique = true)
    private Long imageId;

    @Lob
    @Column(name = "image")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "office_map_id")
    private Office officeMapId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "office_photo_id")
    private Office officeIconId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="office_photos_id")
    private Office officePhotosId;

    @Override
    public String toString() {
        return "Image [imageId=" + imageId
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
        Image image = (Image) obj;
        return image.equals(image.imageId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
        return result;
    }

}
