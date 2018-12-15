package com.iitu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */
@MappedSuperclass
public class Users extends AuditModel implements Serializable{

    public Users(@NotBlank @Size(min = 3, max = 100) String firstName, @NotBlank @Size(min = 3, max = 100) String lastName, @Size(min = 3, max = 100) String middleName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    public Users() {
    }

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name="first_name")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name="last_name")
    private String lastName;

    @Size(min = 3, max = 100)
    @Column(name="middle_name")
    private String middleName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
