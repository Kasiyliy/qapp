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
@Table(name = "interviews")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_interviews",
        initialValue = 1,
        allocationSize=1
)
public class Interviews extends Users {



    public Interviews(@NotBlank @Size(min = 3, max = 100) String firstName, @NotBlank @Size(min = 3, max = 100) String lastName, @Size(min = 3, max = 100) String middleName, Date birthDate, Interviewers interviewer, Interviewees interviewee, Weeks week, Times times) {
        super(firstName, lastName, middleName, birthDate);
        this.interviewer = interviewer;
        this.interviewee = interviewee;
        this.week = week;
        this.times = times;
    }

    public Interviews(){
    }

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    @NotNull
    private Interviewers interviewer;

    @ManyToOne
    @JoinColumn(name = "interviewee_id")
    @NotNull
    private Interviewees interviewee;

    @ManyToOne
    @JoinColumn(name = "week_id")
    @NotNull
    private Weeks week;

    @ManyToOne
    @JoinColumn(name = "time_id")
    @NotNull
    private Times times;

    @NotNull
    @Column(name = "exact_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exactDay = new Date();

    public Interviewers getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewers interviewer) {
        this.interviewer = interviewer;
    }

    public Interviewees getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(Interviewees interviewee) {
        this.interviewee = interviewee;
    }

    public Weeks getWeek() {
        return week;
    }

    public void setWeek(Weeks week) {
        this.week = week;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public Date getExactDay() {
        return exactDay;
    }

    public void setExactDay(Date exactDay) {
        this.exactDay = exactDay;
    }
}
