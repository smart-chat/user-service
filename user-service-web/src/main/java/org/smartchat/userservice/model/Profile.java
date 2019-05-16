package org.smartchat.userservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.smartchat.userservice.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROFILE_TBL")
public class Profile {
    @Id
    @GeneratedValue(generator = "profile_generator_gen")
    @GenericGenerator(name = "profile_generator_gen", strategy = "sequence-identity",
            parameters = @Parameter(name = "sequence", value = "seq_profile"))
    @Column(name = "profile_id", unique = true, updatable = false, nullable = false)
    private long profileId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToOne(mappedBy = "profile")
    private User user;

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return new EqualsBuilder()
                .append(profileId, profile.profileId)
                .append(updatedDate, profile.updatedDate)
                .append(firstName, profile.firstName)
                .append(lastName, profile.lastName)
                .append(gender, profile.gender)
                .append(phone, profile.phone)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(profileId)
                .append(firstName)
                .append(lastName)
                .append(gender)
                .append(phone)
                .append(updatedDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("profileId", profileId)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("gender", gender)
                .append("phone", phone)
                .append("updatedDate", updatedDate)
                .toString();
    }
}

