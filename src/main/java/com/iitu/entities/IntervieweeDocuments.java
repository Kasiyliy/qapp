package com.iitu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */

@Table
@Entity(name = "interviewee_documents")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_interviewee_documents",
        initialValue = 1,
        allocationSize=1
)
public class IntervieweeDocuments extends AuditModel {

    public IntervieweeDocuments() {
    }

    public IntervieweeDocuments(Interviewees interviewee, @NotNull int iin, String birthPlace, String nationality, String issuer, LocalDate issueDate, LocalDate validityDate) {
        this.interviewee = interviewee;
        this.iin = iin;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
    }

    @OneToOne
    @JoinColumn(name = "interviewee_id")
    @NotNull
    private Interviewees interviewee;

    @NotNull
    @Column(name = "iin")
    private int iin;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name="nationality")
    private String nationality;

    @Column(name="issuer")
    private String issuer;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "validity_date")
    private LocalDate validityDate;

    public Interviewees getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(Interviewees interviewee) {
        this.interviewee = interviewee;
    }

    public int getIin() {
        return iin;
    }

    public void setIin(int iin) {
        this.iin = iin;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }
}
