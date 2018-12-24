package com.iitu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Entity
@Table(name = "interviewers")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_interviewers",
        initialValue = 1,
        allocationSize=1
)
public class Interviewers extends Users {

    public Interviewers(String firstName, @NotBlank @Size(min = 3, max = 100) String lastName, @Size(min = 3, max = 100) String middleName, Date birthDate, Statuses status) {
        super(firstName, lastName, middleName, birthDate);
        this.status = status;
    }

    public Interviewers() {
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    @NotNull
    private Statuses status;



    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }
}
