package com.iitu.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */
@Entity
@Table(name="interviewees")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_interviewees",
        initialValue = 1,
        allocationSize=1
)
public class Interviewees extends Users{

    public Interviewees() {
    }

    public Interviewees(@NotBlank @Size(min = 3, max = 100) String firstName, @NotBlank @Size(min = 3, max = 100) String lastName, @Size(min = 3, max = 100) String middleName, Date birthDate, IntervieweeDocuments intervieweeDocument, String schoolName) {
        super(firstName, lastName, middleName, birthDate);
        this.intervieweeDocument = intervieweeDocument;
        this.schoolName = schoolName;
    }

    @OneToOne(mappedBy = "interviewee")
    @NotNull
    private IntervieweeDocuments intervieweeDocument;

    @Column(name = "school_name")
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public IntervieweeDocuments getIntervieweeDocument() {
        return intervieweeDocument;
    }

    public void setIntervieweeDocument(IntervieweeDocuments intervieweeDocument) {
        this.intervieweeDocument = intervieweeDocument;
    }
}
