package org.spm.coworking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * A ClientSystem.
 */
@Data
@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForCommonInfo")
    @SequenceGenerator(name = "sequenceGeneratorForCommonInfo")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "roles")
    @ElementCollection(targetClass = Rle.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Rle> roles;

    @Column(name = "login", nullable = false, length = 255)
    private String login;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @OneToMany(mappedBy="adminId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Office> managedOffices;

    @OneToMany(mappedBy="userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public void setRoles(Set<Rle> roles) {
        this.roles = roles;
    }

    public void setFio(String test) {
        this.fio = test;
    }

    @Override
    public String toString() {
        return "UserProfile [userId=" + userId
                + ", login=" + login
                + ", fio=" + fio
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

        UserProfile userProfile = (UserProfile) obj;
        return userProfile.equals(userProfile.userId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((fio == null) ? 0 : fio.hashCode());
        result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
        return result;
    }
}
