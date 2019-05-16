package org.smartchat.userservice.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class UserDto {
    private long userId;
    private String username;
    private String password;
    private String email;
    private Date registeredDate;
    private Date loginDate;
    private Date updatedDate;

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
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        return new EqualsBuilder()
                .append(userId, userDto.userId)
                .append(registeredDate, userDto.registeredDate)
                .append(loginDate, userDto.loginDate)
                .append(updatedDate, userDto.updatedDate)
                .append(username, userDto.username)
                .append(password, userDto.password)
                .append(email, userDto.email)
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
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("userId", userId)
                .append("username", username)
                .append("email", email)
                .append("registeredDate", registeredDate)
                .append("loginDate", loginDate)
                .append("updatedDate", updatedDate)
                .toString();
    }
}
