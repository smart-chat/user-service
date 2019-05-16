package org.smartchat.userservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.smartchat.userservice.utils.Encryptor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(generator = "user_generator_gen")
    @GenericGenerator(name = "user_generator_gen", strategy = "sequence-identity",
            parameters = @Parameter(name = "sequence", value = "seq_user"))
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private long userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Encryptor.getHash(username+password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(userId, user.userId)
                .append(registeredDate, user.registeredDate)
                .append(loginDate, user.loginDate)
                .append(updatedDate, user.updatedDate)
                .append(username, user.username)
                .append(password, user.password)
                .append(email, user.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(username)
                .append(password)
                .append(email)
                .append(registeredDate)
                .append(loginDate)
                .append(updatedDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("registeredDate", registeredDate)
                .append("loginDate", loginDate)
                .append("updatedDate", updatedDate)
                .toString();
    }
}

