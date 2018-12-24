package com.iitu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */

@Entity
@Table(name="busy_times")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_busy_times",
        initialValue = 1,
        allocationSize=1
)
public class BusyTimes extends AuditModel{

    public BusyTimes() {
    }

    public BusyTimes(@NotNull Weeks week, @NotNull Times times, @NotNull Interviewers interviewer) {
        this.week = week;
        this.times = times;
        this.interviewer = interviewer;
    }

    @ManyToOne
    @JoinColumn(name = "week_id")
    @NotNull
    private Weeks week;

    @ManyToOne
    @JoinColumn(name = "time_id")
    @NotNull
    private Times times;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    @NotNull
    private Interviewers interviewer;

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

    public Interviewers getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewers interviewer) {
        this.interviewer = interviewer;
    }
}
