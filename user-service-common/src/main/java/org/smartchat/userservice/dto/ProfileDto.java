package org.smartchat.userservice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.smartchat.userservice.enums.Gender;

public class ProfileDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phone;
    private long updatedDate;

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

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("gender", gender)
                .append("phone", phone)
                .append("updatedDate", updatedDate)
                .toString();
    }
}
